# Screenplay Workshop

**WORK IN PROGRESS**

## Script

### Part 0: Introduction

Hello and welcome to this course about the screenplay design pattern.

My name is Michael Kutz, and I'll be your instructor.

In this course we will look at an existing test suite written in Java™ using the Page Object pattern.
 
We will analyse what the shortcomings of that well-known pattern are and how screenplay can help us there.

After that we will one-by-one introduce the concepts of screenplay and recreate the existing test suite screenplay-based.

If you're not familiar with Selenium in Java, or the page object pattern, I highly recommend taking the course [Selenium WebDriver with Java](https://testautomationu.applitools.com/selenium-webdriver-tutorial-java/) by [Angie Jones](https://testautomationu.applitools.com/instructors/angie_jones.html).

You can find the link to the GitHub repository below this video, so you can checkout the code and follow the refactoring steps I take yourself. All you will need is
- a Java SDK &geq; v11\
  If you're running a Unix-like OS, I can highly recommend to use [SDKMan](https://sdkman.io) to get and manage Java (and many other) SDKs.
- an IDE\
  I will use [IntelliJ IDEA](https://www.jetbrains.com/idea/) in this course. You can just download the community edition for free or use your preferred IDE instead.

### Part 1: Page Object VS Screenplay Pattern

The Page Object pattern is an effective and well-supported way to organize test code.

A page object contains locators for page elements we want to interact with, so it abstracts from the page structure.

In may also contain methods to abstract complex operations, like filling a form.

Additionally, we can hide away waiting code to prevent interacting with elements, that are not yet ready for interaction

Page objects' methods are typically limited to simple *interactions* on a single page.

This is perfectly OK for simple tests, like a [LoginTest].

In more complex scenarios, we end up with a lot of fine-grained steps, and it can become hard to gasp what the test's purpose is. An example in this test suite would be the [TransferFundsTest].

The manual test case for this feature, it would be:

> Given that the user has opened a new account\
> When they transfer $10 form the main account to the new account\
> Then the new account's balance is increased by that amount\
> And the main account's balance is decreased by that amount.

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

Quite a lot of boring code!

In the manual test case, we can simply rely on the tester to memorize or note down all the facts that we need in the then-part of the test. For example, the main account ID could be noted down right after registration.

Of course, we could store these facts in constants –just like the username and password– but that will cause frequent updates to the tests every time somebody clears the test database. 

The underlying problem with page objects is that they are stateless. Just like the pageobject.pages, that they represent.

We have no simple way of noting things down, but right there in the test causing all that noise.

In modern web applications state is stored mainly on the users' side via cookies, that refer to session data.

So in order to fix our noisy test code problem, we might want a representation of the user. Well, that's the basic idea of the screenplay pattern!

### Part 2: Actors and Tasks

The central object in a screenplay is an __actor__.

The Actor can _have_ __abilities__. For example browsing the web, using an app, gather information via an API, read their email and so on.

So let's start creating some screenplays using these concepts.

First I will create a new package `screenplay` next to the existing `pageobjects` package, so we can develop our screenplays in parallel and compare the code.

As you can see, all existing tests extend [BaseTest] which does the basic common setup, so let's create a [BaseScreenplay] first to do the same.

OK, so instead of the [HomePage] or the WebDriver, we will use an Actor instance to interact with the application. So let's create that class.

...

### Part 3: Questions and Facts

The abilities can be used to _perform_ __tasks__ like register, login, open a new account or to answer __questions__ like "Are you logged in?" or "What's you main account's balance?".

An actor can also _know_ __facts__ like their full name, credentials, address or social security number.

Actors can also _learn_ new facts as they perform tasks. For example, they can memorize the account ID of a new account as they open it.

...

## TODO

- [X] Read [Serenity Screenplay Tutorial]
- [X] Read [4 Top Automation Testing Design Patterns]
- [X] Listen [Test Guild Podcast]
- [X] Read [Page Objects Refactored]

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


[BaseTest]: <src/test/java/pageobjects/BaseTest.java>
[LoginTest]: <src/test/java/pageobjects/LoginTest.java>
[TransferFundsTest]: <src/test/java/pageobjects/TransferFundsTest.java>
[HomePage]: <src/main/java/pageobject/pages/HomePage.java>