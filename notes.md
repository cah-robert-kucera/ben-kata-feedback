# Overview

Thanks for doing this Ben!

These katas are always a lot of work, and while they can be fun to solve a problem, focusing on being anal about test driving adds a really unique twist.

I appreciate you taking the time and focus to work through this. I think this sort of thing is really great practice and affords a certain focus you just can't get in normal card work. I appreciated you diving into kotlin and exploring its idioms and features.

I try to break this into a section on the current iteration of the code, and a section of things I noticed while going from commit to commit.

I also made various changes to the code itself and added comments. This allows you to page through a diff and look at possible refactors or just other approaches you can consider.


## Current

It looks to me like while doing TDD you'd sometimes write more than one test per commit, or commit without all tests passing. In strict TDD you want to write one test, make it pass, possibly refactor, and then commit. And then repeat. That way a commit will show just one test and just the code added specific to that test, and your code should pass all tests on every commit.

When setting up a project, you'll want a gitignore file to exclude common build artefacts or ide meta data. These files are both generated and sometimes user unique. (Some people use intellij, others eclipse, or store user specific preferences). That said, to build everywhere, you'll want to use something like maven or gradle to build the project in a way that can be repeated on everyone's computer.


I thought your test names were really nice. Well done being specific, clear, and not including redundant things like naming a test with `test` in the name.
```
 fun `coins have appropriate values associated with them when used`() {
```


## Commits

a3c84ce
- you have implementation here without a test that's driving it

3ef9790, 208aef5 
- You want to do a failing test, make it pass, then  commit

893e7c4
- neat you were doing by PRs and product feature. I'd say for a kata it's enough just to branch and commit to master with no github involved, especially if you've not done that much before, but this isn't a bad take!

6c1505e
- looks like 3 tests in one commit
