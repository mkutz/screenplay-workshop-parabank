# Screenplay Workshop

**WORK IN PROGRESS**

## Script

### Part 0: Introduction

Hello and welcome to this course about the screenplay design pattern.

My name is Michael Kutz, and I'll be your instructor.

In this course we will look at an existing test suite written in Java using the Page Object pattern.
 
We will analyse what the shortcomings of that well-known pattern are and how screenplay can help us there.

After that we will one-by-one introduce the concepts of screenplay and recreate the existing test suite screenplay-based.

If you're not familiar with Selenium in Java, or the page object pattern, I highly recommend taking the course [Selenium WebDriver with Java](https://testautomationu.applitools.com/selenium-webdriver-tutorial-java/) by [Angie Jones](https://testautomationu.applitools.com/instructors/angie_jones.html).

You can find the link to the GitHub repository below this video, so you can checkout the code and follow the refactoring steps I take yourself. All you will need is
- a Java SDK &geq; v11\
  If you're running a Unix-like OS, I can highly recommend to use [SDKMan](https://sdkman.io) to get and manage Java (and many other) SDKs.
- an IDE\
  I will use [IntelliJ IDEA](https://www.jetbrains.com/idea/) in this course. You can just download the community edition for free or use your preferred IDE instead.

### Part 1: Page Object VS Screenplay Pattern

Welcome back to part 1.

The Page Object pattern is an effective and well-supported way to organize test code.

A page object like [RegisterPage] contains locators for page elements we want to interact with, so it abstracts from the page structure.

It also contains methods to abstract complex operations, like filling the full address into the form in only one step.

We can also hide waiting code, to prevent interacting with elements, that are not yet ready for it, like in the [AccountsOverviewPage]'s constructor.

Or we can put in methods to extract information for easier assertions, like `getAccountIdsList`, which extracts all account IDs from the table or `getAccountBalanceInCents`, which gets the balance of a single account as an integer of cents.

However, page objects' methods are typically limited to simple _interactions_ on a single page.

This is perfectly OK for simple tests, like a [LoginTest], which is in fact limited to the home page only.

In more complex scenarios, we end up with a lot of fine-grained steps, and it can become hard to gasp what the test's purpose is. An example in this test suite would be the [TransferFundsTest].

The manual test case for this feature, it would be something like:

> Given that the user has opened a new account\
> When they transfer $10 form the main account to the new account\
> Then the new account's balance increased by that amount\
> And the main account's balance decreased by that amount.

Let's read the code:

> Given that the user has opened a new account\
> And memorizes the new account's ID\
> And the user clicks the account overview link\
> And memorizes their main account's ID and balance\
> And memorizes the new account's balance\
> When the user clicks the transfer funds link\
> And transfers $10 from the main account to the new account
> Then the user clicks the account overview link\
> And sees that the new account's balance increased by the transfer amount
> And the main account's balance decreased by the transfer amount.

Quite a lot of boring code that makes it hard to see what the test is actually about.

In the manual test case, we can simply rely on the tester to memorize or note down all the facts that we need in the then-part of the test. For example, the main account ID could be noted down right after registration.

Of course, we could store these facts in constants –just like the username and password– but that will cause frequent updates to the tests every time somebody clears the test database.

The underlying problem with page objects is that they are stateless. Just like the pages, that they represent.

We have no simple way of noting things down, but saving it in local variables right there in the test.

In modern web applications state is stored mainly on the users' side via cookies, that refer to session data.

So in order to fix our noisy test code problem, we might want a representation of the user. Well, that's the basic idea of the screenplay pattern!

### Part 2: Actors and Tasks

The central object in a screenplay is an __actor__.

The Actor can _have_ __abilities__. For example browsing the web, using an app, gather information via an API, read their email and so on.

These abilities enable the actor to _perform_ __tasks__, like login, transfer funds or open a new account.

So let's start creating some screenplays using these three concepts.

First I will create a new package `screenplay` next to the existing `pageobject` package, so we can develop our screenplays in parallel and compare the code.

As you can see, all existing tests extend [BaseTest] which does the basic common setup, so let's create a [BaseScreenplay] first to do the same.

OK, so instead of the [HomePage], we will use an [Actor] instance to interact with the application. So let's create that class.

Just like in the [BaseTest], we want to make sure our WebDriver is set up before, and teared down after all tests have been executed, so we can copy these to methods over.

We also like delete all cookies and have the driver set the main page before each test, so we can copy the `reset` method as well.

Instead of the [HomePage], let's create a new class [Actor] and create an instance as a field of our [BaseScreenplay]. As the name of the field, we should use the __role__ the actor will play. In our case that role will be a simple user, so let's name the field `user`.

For logging purposes, let's give the actor a name and override the `toString` method to return it.

In order to be able to interact with our application, the [Actor] need abilities, so let's create a Set of abilities as a field of our [Actor]. [Ability] should be an interface to allow creating different abilities for the actor.

To add abilities to the [Actor], we  create a method `can`, which will return `this`, so we chain methods like this and set up and assign an [Actor] instance in only one line.

To access abilities, we create a method `getAbiliy` which will return the instance of the requested [Ability] class or throw a [MissingAbilityException], which should tell us in its message who was not able to do what. So let's create that.

Let's implement that interface and create the [BrowseTheWeb] ability, which simply wraps a WebDriver instance and provides a getter for it.

Since we are storing abilities in a HashSet, we should also override `equals` and `hashCode` and for logging purposes, we also override `toString`. So let's rely on our IDE to generate those.

Now let's instantiate the [BrowseTheWeb] in our [BaseScreenplay] and add it to the [Actor]'s abilities using the `can` method.

Reading the setup line now is still a bit strange due to the `new` keyword. We can get rid of that by using a static method to initialize [BrowseTheWeb]. Let's call it `browseTheWebWith`, to make the line almost plain English.

OK, so now we are setup to create a [LoginScreenplay]. In order to do that, we need the [Actor] to be able to perform __tasks__, in this case a login. So let's create another method `perform` in our [Actor] class which takes an instance of the [Task] interface.

A [Task] can be performed by an [Actor], so we will declare a method `performAs`, which takes an [Actor] instance.

Next we will implement the [Login]. Obviously a login requires a username and a password, so we add those as fields and implement the `performAs` method.

As the login will happen using a WebDriver, we first need to use the [Actor]'s `getAbility` method to get the [BrowseTheWeb] ability and use its WebDriver instance. This may appear complicated, but allows us to combine different [Ability] instances to perform complex tasks.

The actual login code can be copied from the [HomePage] class' `login` method. We only need to inline the locators.

OK, let's execute the test to see if our task is performed as expected.

As you can see the browser opens, and the login is happening just like in [LoginTest].

We are still missing an essential part of a test: the verification. This we will address in the next part.

### Part 3: Asking Questions

To finish our first screenplay, we need to answer the __question__ if the user is logged in now.

So let's create another method in our [Actor] `seesThat` which takes an instance of the new [Question] interface. Similar to a [Task] being performed by an [Actor], a [Question] must be answered by one. So we create a method `answeredBy` taking an [Actor] as argument.

Unlike a [Task] a Question should return us an answer. In case of our first Question "is the user logged in?", that answer is either yes or no. So the type of the answer is `Boolean`, but there will be Questions with other types of answers, so let's use Java generics to make the return type of `answeredBy` whatever the question requires.

To implement our first [Question] "[LoggedIn]", we again need to use the actor's [BrowseTheWeb] ability and extract the WebDriver, then we can copy the code from the [HomePage]'s `isLoggedIn` method.

Again, we need to inline the locators, and we also generate `equals`, `hashCode` and `toString`.

To make the code more readable, we will again add a static method to create an instance of [LoggedIn], named `loggedIn`. Now let's use the method within `seesThat` in `assertThat` to verify the effect in our test.

Now let's run our screenplay, which is finally equivalent to the original [LoginTest].

As a homework task, have a look at [RegisterTest] and try to create an equivalent [RegisterScreenplay].

### Part 4: Knowing Facts

Welcome back to part 4.

I hope you were able to create [RegisterScreenplay].

Let's have a look into my version of it. As you can see the new [Register] task requires a lot of test data, that are still right there in the screenplay's code. 

In a manual test case, we'd refer to a fixed set of test data, which is used in any test case that needs something like a valid social security number or an address.

Our testers would probably know them by heart after a few iterations.

Why don't we make our [Actor] know things by heart as well?

So let's create a new method `knows`, which takes an instance of the new [Fact] interface and stores it in another Set named `facts`. To use facts, we can basically copy the `getAbility` method and adjust it to get `Fact`s and to throw a [MissingFactException] with a reasonable message if a fact in unknown to the actor.

As we will mainly use the `knows` method in our [BaseScreenplay]'s `setupActor` method –just like `can`–, we should also return the actor instance.

Now we can create some facts. Let's start with the actor's [Credentials] as they are used in both existing screenplays.

So we implement the [Fact] interface and add one String field for the user name and one for the password plus the corresponding getter methods. We also add the usual static method to create an instance of [Credentials].

After the actor knows their credentials, now we can remove the username and password fields from the [Login] task. Instead, we can now call `getFact` on the performing actor instance to get the required facts.

Let's run [LoginScreenplay] again to check, if it still works the way it should.

Let's improve this a bit further by putting our magically working user name and password into a new static method `defaultCredentials`, which does not take any parameters.

These default test data methods make using [Fact]s very convenient. It reduces the risk of fact duplications among our tests, since we can get the magic default values from anywhere, and if they change for some reason, we can also update them in exactly one place.

As another homework exercise, try to use [Fact]s in your [RegisterScreenplay].

### Part 5: Learning Facts by performing Tasks

Welcome back to part 5.

Let's have a short look into the revised [RegisterScreenplay].

I added a lot of more implementations of the Fact interface:
- [FullName],
- [Address],
- [PhoneNumber],
- [Ssn]

I also added a new static method to [Credentials], which generates a random username, so we don't fail the test due to an already taken username.

So facts do not only provide use with a way to store static default test data, but can also be used to hold logic to generate dynamic test data!

Now let's have a look at [OpenNewAccountTest].

As you can see, the test is using three pages: [OpenNewAccountPage] is used to actually open the new account, but then the [AccountsOverviewPage] is needed to verify that the account using the new account ID from the [AccountOpenedPage].

So let's create our [OpenNewAccountScreenplay], in which we will first need perform [OpenNewAccount] task.

Most of the code can be copied from [OpenNewAccountPage] and [AccountOpenedPage]. Note that this includes the waiting code from the constructors, which is needed since the pages are not intractable immediately.

Let's check if our new task works as expected.

So we created a new account, now we need to verify it on the [AccountsOverviewPage] and in order to do that, we need the new account's ID, which was displayed on the [AccountOpenedPage].

Since [Task]s' `performAs` method does not return anything, we cannot just record it in a local variable as in [OpenNewAccountTest].

Well after all, the new account's ID is simply a new fact. One that was not known before the test started, but a fact after all. So why don't we just use the `knows` method of our [Actor] to _learn_ the new fact, while we perform a task.

Let's add an assertion to make sure the actor has learned the ID after performing the task.

Now we can add the new [AccountBalance] question to verify the initial balance of our account.



## TODO

- [X] Read [Serenity Screenplay Tutorial]
- [X] Read [4 Top Automation Testing Design Patterns]
- [X] Listen [Test Guild Podcast]
- [X] Read [Page Objects Refactored]
- [ ] Add questions of part 1
- [ ] Add questions of part 2
- [X] Write part 3:
  - Introduce Questions
  - Implment LoggedIn as first question
- [ ] Add questions of part 3
- [X] Write part 4:
  - Introduce Credentials as first known Fact
- [ ] Write part 5:
  - Implement OpenNewAccountScreenplay and learn the new account's ID

## Notes

- [Serenity Screenplay Tutorial]
  - Described by [Antony Marcano] with contributions from [Andy Palmer], [Jan Molak]
  - Formerly known as "Journey Pattern"
- [Test Guild Podcast]
  - There is a framework helping to implement: [Serenity Framework]
  sitting on top of Cucumber/Selenium/others
  - More proactive, dynamic → why? how?
  - Why did my test fail? Easier with Sceenplay
  - Same as page object: give code a place outside of tests
  - Actors, Tasks, Goals → express tests in business terms
  - Actor has *goals*, requires *tasks*
  - Task consists of *interactions* with the system
  - Talk: Antony Marcano → Selenium Conference
  - Angie Jones: Might be overkill (hasn't tried it, though)
  - Dave Haffner: Logical evolution of page object, SOLID pricipals
  - Page objects reduced to basics: selectors basically
  - Seems to be a good choice for not-so-technical testers (→ John Smart in [Test Guild Podcast])
  - Testers think of goals and tasks; Devs then implement interactions
- [Page Objects Refactored]
  - Page objects violate SOLID principles, especially Open Closed and Single Responsibility
  - Roles: Who is this for?\
    ⮡ Goals: Why are they here and what outcome do they hope for?\
    ⮡ Tasks: What will they need to do to achieve these goals?\
    ⮡ Actions: How they complete each task through specific interactions?
- [4 Top Automation Testing Design Patterns] 

[Serenity Framework]: <http://www.thucydides.info/#/whatisserenity>
[4 Top Automation Testing Design Patterns]: <https://testguild.com/automation-testing-design-patterns/>
[Test Guild Podcast]: <https://testguild.com/podcast/automation/138-screenplay-pattern-better-page-objects-john-smart/>
[Serenity Screenplay Tutorial]: <http://serenity-bdd.info/docs/articles/screenplay-tutorial.html>
[Antony Marcano]: <https://twitter.com/AntonyMarcano>
[Andy Palmer]: <https://twitter.com/AndyPalmer>
[Jan Molak]: <https://twitter.com/JanMolak>
[John Ferguson Smart]: <https://twitter.com/wakaleo>
[Page Objects Refactored]: <https://ideas.riverglide.com/page-objects-refactored-12ec3541990#.ekkiguobe>

[RegisterPage]: <src/main/java/pageobject/pages/RegisterPage.java>
[AccountsOverviewPage]: <src/main/java/pageobject/pages/AccountsOverviewPage.java>
[AccountOpenedPage]: <src/main/java/pageobject/pages/AccountOpenedPage.java>
[OpenNewAccountPage]: <src/main/java/pageobject/pages/OpenNewAccountPage.java>
[BaseTest]: <src/test/java/pageobjects/BaseTest.java>
[LoginTest]: <src/test/java/pageobjects/LoginTest.java>
[TransferFundsTest]: <src/test/java/pageobjects/TransferFundsTest.java>
[HomePage]: <src/main/java/pageobject/pages/HomePage.java>
[Actor]: <src/main/java/screenplay/Actor.java>
[BaseScreenplay]: <src/test/java/screenplay/BaseScreenplay.java>
[Ability]: <src/main/java/screenplay/abilities/Ability.java>
[BrowseTheWeb]: <src/main/java/screenplay/abilities/BrowseTheWeb.java>
[LoginScreenplay]: <src/test/java/screenplay/LoginScreenplay.java>
[Task]: <src/main/java/screenplay/tasks/Task.java>
[Login]: <src/main/java/screenplay/tasks/Login.java>
[Question]: <src/main/java/screenplay/questions/Question.java>
[LoggedIn]: <src/main/java/screenplay/questions/LoggedIn.java>
[MissingAbilityException]: <src/main/java/screenplay/MissingAbilityException.java>
[RegisterTest]: <src/test/java/pageobjects/RegisterTest.java>
[RegisterScreenplay]: <src/test/java/screenplay/RegisterScreenplay.java>
[Fact]: <src/main/java/screenplay/facts/Fact.java>
[MissingFactException]: <src/main/java/screenplay/MissingFactException.java>
[Credentials]: <src/main/java/screenplay/facts/Credentials.java>
[FullName]: <src/main/java/screenplay/facts/FullName.java>
[Address]: <src/main/java/screenplay/facts/Address.java>
[Ssn]: <src/main/java/screenplay/facts/Ssn.java>
[PhoneNumber]: <src/main/java/screenplay/facts/PhoneNumber.java>
[OpenNewAccountTest]: <src/test/java/pageobjects/OpenNewAccountTest.java>
[OpenNewAccount]: <src/main/java/screenplay/tasks/OpenNewAccount.java>
[OpenNewAccountScreenplay]: <src/test/java/screenplay/OpenNewAccountScreenplay.java>
[AccountBalance]: <src/main/java/screenplay/questions/AccountBalance.java>
