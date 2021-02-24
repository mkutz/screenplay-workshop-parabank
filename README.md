# Screenplay Workshop

**WORK IN PROGRESS**

## Script

### Part 0: Introduction

→ Presentation at <https://slides.com/mkutz/screenplay-design-pattern-course>

Hello and welcome to this course about the screenplay design pattern.

My name is Michael Kutz, and I'll be your instructor.

In this course we will work with an existing test suite written in Java using the Page Object pattern.

In order to follow my steps, you will need three things on your computer:

- a Java Development Kit of version 11 or higher,
- an IDE like IntelliJ IDEA, which is the one I'll use throughout the course,
- and the code that you can find on the GitHub URL below this video.

First, we will analyze what the shortcomings of Page Objects are, followed by a brief introduction of the basic
principles of Screenplay.

After that we will one-by-one introduce the concepts of the Screenplay design pattern and recreate the tests using them

- Staring with Actors and Abilities,
- followed by performing Tasks
- and answering Questions.
- After these we'll explore Facts, which are an extension to the original/pure Screenplay.
- In the last part, we'll compare the original Page Objects implementation with the Screenplay one and see the
  advantages or disadvantages of the two approaches.

So, at the end of the course you will have clear understanding about

- What is the screenplay pattern is
- What is needed to apply it
- How you can transform your existing tests
- Why and when screenplay is better than page objects

If you're not familiar with Selenium in Java, or the page object pattern, I highly recommend taking the
course [Selenium WebDriver with Java](https://testautomationu.applitools.com/selenium-webdriver-tutorial-java/)
by [Angie Jones](https://testautomationu.applitools.com/instructors/angie_jones.html).

### Part 1: Page Objects

Welcome back to part 1.

→ Presentation at https://slides.com/mkutz/screenplay-design-pattern-course Page Objects and Screenplay

Let's have quick look into Page Objects.

A test can interact with an element of a page using pure Selenium locators.

However, if we add another test, it is likely it will need to interact with the same elements also used in our first test.

So we have some code duplication and if the locator for an element needs to be changed, we'd need to change it in every single test using it.

Using a Page Object, which contains all the selectors, allows us to define all our locators in one place, avoiding the code duplication.

It may also contain methods to abstract complex operations, like filling the full address into the form in only one step.

Let's checkout the code and take a look into some typical Page Objects.

// Switch to Idea // Navigate to [RegisterPage]

The RegisterPage for example contains a lot of locators as static fields.

These are used in several methods for filling all the fields on it.

// Show the page

// Switch to [AccountsOverviewPage]

The [AccountsOverviewPage] is even more complex.

`getAccountBalanceInCents` gets the balance of a single account as an integer of cents identified by its index or its ID.

// Highlight `getAccountBalanceInCents`' versions

Such methods make verifications much easier to read, compared to using such code in tests directly.

// Show example from [TransferFundsTest]

As the account rows are not initially visible but loaded dynamically, its methods rely on some `WebDriverWait`s to avoid false return values due to premature reading.

// Highlight waiting code in the methods

However useful, page objects' methods are typically limited to simple _interactions_ on a _single_ page.

This is perfectly OK for simple tests, like a [LoginTest], which is in fact limited to the home page only.

// Switch to [LoginTest]

In more complex scenarios, we end up with a lot of fine-grained steps, and it can become hard to gasp what the test's purpose is.

An example in this test suite would be the [TransferFundsTest].

The manual test case for this feature, it would be something like:

> Given that the user has at least two accounts\
> When they transfer $10 form their first to the second account\
> Then the first account's balance decreased by that amount\
> And the second account's balance increased by that amount.

Let's read the code:

> Given that the user clicks the open new account link\
> And opens a new account\
> And the user clicks the account overview link\
> And memorizes the first account's balance\
> And memorizes the second account's balance\
> When the user clicks the transfer funds link\
> And transfers $10 from the first account to the second account\
> And the user clicks the account overview link\
> Then the user sees that the first account's balance is decreased by the amount\
> And the second account's balance increased by the amount.

Quite a lot of boring code that makes it hard to see what the test is actually about.

But, as the opening of a new account spans several pages, we cannot put open a new account into one line.

Of course, we could just rely on a prepared account, which already possesses two accounts with a fixed balance, but that required some tight test data management, which –in my experience– ultimately fails. 
Setting the stage using API calls would also be a brilliant idea, but let's assume we cannot do that right now.

Screenplay gives us a representation of the user and of tasks, which can span as many pages as needed.
In part 2, you'll see how this clears a lot of the noisy code away.

Another problem with Page Objects is that they can turn into very large classes, that become hard to maintain as they
are used by a lot of tests. Selectors and interaction code will be added for quite special test cases, which are meant
to be exclusively used in those.

When one developer/tester changes the code to optimize for the task at hand, they might break another test, they forgot
about. This ultimately discourages refactorings and the code base becomes unmaintainable.

As Screenplays use one class per task, that an Actor can perform, these classes usually stay rather small and can be
optimized for the one use case only.

#### Questions

- Page Objects typically hold
    -[ ] Locators and interaction code for one specific page
    -[ ] Implementations of use cases
    -[ ] All HTML elements of a page
- What is not a problem when using Page Objects
    -[ ] Page Objects can grow quite big
    -[ ] Use cases spanning multiple pages need to be split
    -[ ] They make tests slow

### Part 2: Actors and Abilities

Welcome to part 2.

→ Presentation at https://slides.com/mkutz/screenplay-design-pattern-course Actors with Abilities

Now that we had a look into the disadvantages of Page Objects, let's see how Screenplay can improve our tests.

The central object in a __screenplay__ is an __actor__.

The Actor can have __abilities__, which enable __actions__, which interact with __elements__ of our applicaton.
For example, the ability to __browse the web__ enables the actor to perform actions like clicking a link element or reading the text of a paragraph element on a website.

Other abilities could enable the actor to call APIs or receive email.

However, in this course we'll focus on interacting with the Parabank website only.

So let's start creating some screenplays using these three concepts.

// Switch to Idea

First I will create a new package `screenplay` next to the existing `pageobject` package, so we can develop our screenplays in parallel and compare the code.

// Create screenplay package

As you can see, all existing tests extend [BaseTest] which does the basic common setup, so let's create a [BaseScreenplay] first to do the same.

// Copy BaseTest, rename to BaseScreenplay

Instead of the [HomePage], let's create a new class [Actor] and create an instance as a field of our [BaseScreenplay].
As the name of the field, we should use the __role__ the actor will play. In our case that role will be a simple user,
so let's name the field `user`.

// Replace HomePage field with Actor

So let's create that class.

// Create Actor class

For logging purposes, let's give the actor a name and override the `toString` method to return it.

// Create `name` field
// Override `toString` with return name
// Switch back to BaseScreenplay

We could now simply give the WebDriver to the Actor and start adding methods, like `login`, `register` or even `transferFunds` to the [Actor] class.
This would be pretty straight forward, and our tests would gain some readability.
We could put knowledge and state into fields of our actor instance.

However, we would end up with a class even larger than our largest page objects as this class would be a collection of
_all_ use cases of our application!

To avoid that, the screenplay pattern breaks everything down into generic interfaces for the [Actor] to work with.

The first of these interfaces is the [Ability].
Let's create that interface without any methods.
It will simply mark classes as abilities.

// Create [Ability] interface

The one ability we will be working with in this course will be named `BrowseTheWeb`.
It simply wraps a `WebDriver` instance.

// Create `BrowseTheWeb` with `WebDriver` field and getter.

For logging purposes we also override the `toString` method to return which kind of `WebDriver` – and hence which kind of browser – the instance wraps.

// Override `toString`

// Switch to [BaseScreenplay]

In our [BaseScreenplay] we can now wrap the `WebDriver` and give it to the user.
For this we use a new method `can` for our Actor, which takes an Ability object.

To add abilities to the [Actor], we'll use a method called `can`, which will return `this`, so we chain methods like this and
set up and assign an [Actor] instance in only one line.

// Add a call to the `can` method

Let's implement the `can` method.

// Let Idea create the `can` method and switch to it

In order to be able to get an ability from an Actor, we implement a `uses` method, which will return an instance for a requested ability class.

As we want to avoid casts on the caller side, we return not an Ability, but a generic type A, which extends Ability.
This way, we will get a BrowseTheWeb instance instead of a ominous Ability when we ask for it.

Just returning the object from the map does not work, as it would be of type Ability and not A.
There's also the risk that a requested ability is not contained in the actor's abilities, and we don't want to have null checks on the caller side.
By putting the object into an Optional:
- First we use the map method to cast the object into the given ability class,
- Then we throw an exception in case it is not present.

The exception should be a `RuntimeException`, as we usually don't want to catch it.
It should also have a  most concise message that tells us why the screenplay was cancelled/failed.
In this implementation the message might be "John misses the ability to BrowseTheWeb".

// Switch back to BaseScreenplay

Looking at our [BaseScreenplay], it is now ready to direct our [Actor], which also has the [Ability], which will enable __actions__ to interact with the __elements__ of our application.

In the next part we'll transform our first test to the new structure.

#### Questions

- Why don't we just give the WebDriver to the Actor and implement tasks using methods?
  -[ ] Because that's not possible
  -[ ] The actor class would then contain all the use cases of the application and become really huge
  -[ ] There's no reason
- What is the purpose of abilities?
  -[ ] Enable Actions
  -[ ] Provide nicer logging
  -[ ] Direct the actor

### Part 3: Performing Tasks

Welcome to part 3.

→ Presentation at https://slides.com/mkutz/screenplay-design-pattern-course Performing Tasks

Now that we have an [Actor], which has the [Ability] to [BrowseTheWeb]. 
Thanks to that the [Actor] can perform simple actions by directly calling methods on the WebDriver.

We do want to group these fine-grained actions, so our tests don't get too noisy.
Instead of grouping the actions by pages –as Page Objects do– we will group them by meaningful __tasks__.

OK, so now we are set up to create our first screenplay: the [LoginScreenplay].
In order to do that, we need the [Actor]
to be able to perform __tasks__, in this case a login. So let's create another method `does` in our [Actor] class
which takes an instance of the [Task] interface.

// Add `perform` method, taking a `Task` instance // Let Idea create the `Task` interface

A [Task] can be performed by an [Actor], so we will declare a method `performedBy`, which takes an [Actor] instance.

// Add `performedBy` method to the interface, taking an `Actor` instance

Next we will implement the [Login] task. Obviously a login requires a username and a password, so we add those as fields
and implement the `performedBy` method.

// Implement `Task` as `Login` with `performedBy` and username and password as fields

As the login will happen using a WebDriver, we first need to use the [Actor]'s `uses` method to get the [BrowseTheWeb]
ability and get its WebDriver instance. This may appear complicated, but allows us to combine different [Ability]
instances to perform complex tasks.

// Call `uses` from `proformedBy`, to assign the WebDriver to a local variable.

The actual login code can be copied from the [HomePage] class' `login` method.

// Copy `login` code form `HomePage`

Note that the task is a value object.
It is created once and does never need to change its state.
This is what we want.
[Task]s purely describe the actions needed to be performed.

OK, let's execute the test to see if our task performs as expected.

// Execute the LoginScreenplay

As you can see the browser opens, and the login is happening just like in [LoginTest].

To make the code somewhat nicer, we can again create a static initializer for `loginWith`.

// Create `loginWith` in [Login].

We are still missing an essential part of a test: the verification. This we will address in the next part.

#### Questions

- Task objects…
  -[ ] …perform an action and change their state in the process.
  -[ ] …describe the actions that need to be performed by an Actor.
- How is required data given to a Task?
  -[ ] As parameters in its `performedBy` method.
  -[ ] As private final fields.
  -[ ] In a global util class.

### Part 4: Answering Questions

Welcome to part 4.

→ Presentation at https://slides.com/mkutz/screenplay-design-pattern-course Answering Questions

To finish our first screenplay, we need to answer the __question__ if our login was successful.

So let's create another method in our [Actor] `checks` which takes an instance of the new [Question] interface.

Unlike a [Task] a [Question] should return us an answer.
In case of our first [Question] "is the user logged in?", that answer is either yes or no.
So the type of the answer is `Boolean`, but there will be Questions with other types of answers.
Hence, we should use a generic return type.

// Create `<A> A checks(Question<A>)` in Actor
// Let Idea create the `Question<A>` interface

Similar to a [Task] being performed by an [Actor], a [Question] must be __answered by__ one.
So we create a method `answeredBy` taking an [Actor] as argument.

// Add `A answeredBy(Actor)` to the `Question` interface

To implement our first [Question] "[LoggedIn]", we again need to use the actor's [BrowseTheWeb] ability and extract the WebDriver, then we can copy the code from the [HomePage]'s `isLoggedIn` method.

// Implement `LoggedIn` and add code `HomePage.isLoggedIn` to its `answeredBy` method

To make the code more readable, we will again add a static method to create an instance of [LoggedIn], named `loggedIn`.

// Add static initializer `loggedIn` for `LoggedIn`

Now let's go back to the [LoginScreenplay] and ask the actor if they `seesThat` they are `loggedIn` to verify the effect in our test.

// Add `user.checks(loggedIn)` to `LoginScreenplay`

Now let's run our screenplay, which is finally equivalent to the original [LoginTest].

// Execute `LoginScreenplay`

As a homework task, have a look at [RegisterTest] and try to create an equivalent [RegisterScreenplay].

### Part 5: Knowing Facts

Welcome back.

I hope you were able to create [RegisterScreenplay].

Let's have a look into my version of it.

// Open [RegisterScreenplay]

As you can see I've applied a builder pattern to group the various strings required for the [Register] task into logical units, like address or full name.

Still, this requires a lot of test data, that are still right there in the screenplay's code.

In a manual test case, we'd refer to a fixed set of test data, which is used in any test case that needs something like a valid social security number or an address.

Our testers would probably know them by heart after a few iterations.

Why don't we make our [Actor] know things by heart as well?

→ Presentation at https://slides.com/mkutz/screenplay-design-pattern-course Knowing/Learning Facts

This is where I'd like to extend the original idea of Screenplay with an additional concept: __facts__.

So let's create a new interface [Fact] and a new method `learns` taking an instance and store in another Set `facts`.

// Create `Fact` interface, `Actor.learns(Fact)` method and the `facts` field as a `Set<Fact>`

To get a required fact, we crate a new method `remembers`, which either returns the requested fact or throws
a [MissingFactException] if `facts` does not contain the requested fact.

// Create `T Actor.remembers(T)`

As we will mainly use the `learns` method in our [BaseScreenplay]'s setup methods, we should also return the actor instance to be able to chain the calls.

// Make learns return the Actor

Now we can create some facts. Let's start with the actor's [Credentials] as they are used in both existing screenplays.

So we implement the [Fact] interface and add one String field for the username and one for the password plus the corresponding getter methods.
We also add the usual static method to create an instance of [Credentials].

// Implement `Credentials` `Fact`
// Generate `equals`, `hashCode` and `toString`
// Add `learns(credentials(username, password))` to the [BaseScreenplay]

After the actor knows their credentials, now we can remove the username and password fields from the [Login] task.
Instead, we can now call `getFact` on the performing actor instance to get the required facts.

// Inline username and password, use `remembers` in Login

Let's run [LoginScreenplay] again to check, if it still works the way it should.

// Run `LoginScreenplay`

Nice.

Let's improve this a bit further by putting our magically working username and password into a new static
method `defaultCredentials`, which does not take any parameters.

// Add `defaultCredentials` and move username and password from `BaseScreenplay` to `Credentials` class.

These default test data methods make using [Fact]s very convenient.
It reduces the risk of fact duplications among our tests, since we can get the magic default values from anywhere.
If they change for some reason, we can also update them in exactly one place.

As another homework exercise, try to use [Fact]s in your [RegisterScreenplay].

### Part 6: Learning Facts by performing Tasks

Welcome back to part 6.

Let's have a short look into the revised [RegisterScreenplay].

// Open `RegisterScreenplay`

I added a lot of more implementations of the Fact interface:

- [FullName],
- [Address],
- [PhoneNumber],
- [Ssn]

I also created two new sub-packages: `login` and `registration`.

All classes (primarily) related to login –the [Login] task, the [LoggedIn] question and the [Credentials] fact– are now in the login package.
The [Register] task and its facts are now in the registration package.

This separation shows nicely how screenplay allows to keep code related to different features into separate places.

// Scroll through packages

I also added a new static method to [Credentials], which generates a random username, so we don't fail the test due to an already taken username.

// Show `uniqueCredentials` method

So facts do not only provide use with a way to store static default test data, but can also be used to hold logic to generate dynamic test data!

Now let's have a look at [OpenNewAccountTest].

// Open `OpenNewAccountTest`

As you can see, the test is using three pages: [OpenNewAccountPage] is used to actually open the new account, but then the [AccountsOverviewPage] is needed to verify that the account using the new account ID from the [AccountOpenedPage].

// Highlight `newAccountId` variable

So let's create our [OpenNewAccountScreenplay], in which we will first need perform [OpenNewAccount] task.

// Create `OpenNewAccountScreenplay` and implement `OpenNewAccount` `Task`

Most of the code can be copied from [OpenNewAccountPage] and [AccountOpenedPage]. Note that this includes waiting code, which is needed since the pages are not intractable immediately.

// Implement `performAs` with code from `OpenNewAccountPage` and `AccountOpenedPage`

Let's check if our new task works as expected.

// Use `OpenNewAccount` in `OpenNewAccountScreenplay` and execute it

So we created a new account, now we need to verify it on the [AccountsOverviewPage] and in order to do that, we need the
new account's ID, which was displayed on the [AccountOpenedPage].

Since [Task]s' `performAs` method does not return anything, we cannot just record it in a local variable as
in [OpenNewAccountTest].

Well after all, the new account's ID is simply a new fact. One that was not known before the test started, but a fact
after all. So why don't we just use the `learns` method of our [Actor] to _learn_ the new fact, while we perform a task.

// Navigate to `OpenNewAccount.performAs`, add a `learns` call to store the account ID (let Idea create the class)

Let's add an assertion to make sure the actor has learned the ID after performing the task.

// Add `assertNotNull(user.remembers(NewAccountId.class)))`

Now we can add the new [NewAccountBalance] question to verify the initial balance of our account.

// Add the `AccountBalance` `Question` and use it in [OpenNewAccountScreenplay]
// Use implement `answeredBy` using the `NewAccountId` `Fact`

Let's see if our screenplay works as expected.

// execute the test

As you can see [OpenNewAccountScreenplay] does no longer contain a variable for the new account's ID.
We don't really care for the ID in this particular test, so that's just fine.

### Part 7: Comparison

So let's compare our two implementations.

…

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

[RegisterPage]: <src/main/java/pageobject/RegisterPage.java>

[AccountsOverviewPage]: <src/main/java/pageobject/AccountsOverviewPage.java>

[AccountOpenedPage]: <src/main/java/pageobject/AccountOpenedPage.java>

[OpenNewAccountPage]: <src/main/java/pageobject/OpenNewAccountPage.java>

[BaseTest]: <src/test/java/pageobjects/BaseTest.java>

[LoginTest]: <src/test/java/pageobjects/LoginTest.java>

[TransferFundsTest]: <src/test/java/pageobjects/TransferFundsTest.java>

[HomePage]: <src/main/java/pageobject/HomePage.java>

[Actor]: <src/main/java/screenplay/Actor.java>

[BaseScreenplay]: <src/test/java/screenplay/BaseScreenplay.java>

[Ability]: <src/main/java/screenplay/Ability.java>

[BrowseTheWeb]: <src/main/java/screenplay/abilities/BrowseTheWeb.java>

[LoginScreenplay]: <src/test/java/screenplay/LoginScreenplay.java>

[Task]: <src/main/java/screenplay/Task.java>

[Login]: <src/main/java/screenplay/login/Login.java>

[Question]: <src/main/java/screenplay/Question.java>

[LoggedIn]: <src/main/java/screenplay/login/LoggedIn.java>

[MissingAbilityException]: <src/main/java/screenplay/MissingAbilityException.java>

[RegisterTest]: <src/test/java/pageobjects/RegisterTest.java>

[RegisterScreenplay]: <src/test/java/screenplay/registration/RegisterScreenplay.java>

[Fact]: <src/main/java/screenplay/Fact.java>

[MissingFactException]: <src/main/java/screenplay/MissingFactException.java>

[Credentials]: <src/main/java/screenplay/login/Credentials.java>

[FullName]: <src/main/java/screenplay/registration/FullName.java>

[Address]: <src/main/java/screenplay/registration/Address.java>

[Ssn]: <src/main/java/screenplay/registration/Ssn.java>

[PhoneNumber]: <src/main/java/screenplay/registration/PhoneNumber.java>

[OpenNewAccountTest]: <src/test/java/pageobjects/OpenNewAccountTest.java>

[OpenNewAccount]: <src/main/java/screenplay/openaccount/OpenNewAccount.java>

[OpenNewAccountScreenplay]: <src/test/java/screenplay/openaccount/OpenNewAccountScreenplay.java>

[AccountBalance]: <src/main/java/screenplay/openaccount/NewAccountBalance.java>
