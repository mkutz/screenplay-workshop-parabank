# Screenplay Workshop

**WORK IN PROGRESS**

## TODO

- [ ] Read [Serenity Screenplay Tutorial]
- [X] Read [4 Top Automation Testing Design Patterns]
- [x] Listen [Test Guild Podcast]
- [X] Read [Page Objects Refactored]

## Notes

- [Serenity Screenplay Tutorial]
  - Described by [Antony Marcano] with contributions from [Andy Palmer], [Jan Molak]
  - Formally known as "Journey Pattern"
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
  - 

[Serenity Framework]: <http://www.thucydides.info/#/whatisserenity>
[4 Top Automation Testing Design Patterns]: <https://testguild.com/automation-testing-design-patterns/>
[Test Guild Podcast]: <https://testguild.com/podcast/automation/138-screenplay-pattern-better-page-objects-john-smart/>
[Serenity Screenplay Tutorial]: <http://serenity-bdd.info/docs/articles/screenplay-tutorial.html>
[Antony Marcano]: <https://twitter.com/AntonyMarcano>
[Andy Palmer]: <https://twitter.com/AndyPalmer>
[Jan Molak]: <https://twitter.com/JanMolak>
[John Ferguson Smart]: <https://twitter.com/wakaleo>
[Page Objects Refactored]: <https://ideas.riverglide.com/page-objects-refactored-12ec3541990#.ekkiguobe>