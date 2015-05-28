# cis234
Ranking System

Ranking-System-Demo
Ranking System in Java using Eclipse IDE and a MS SQL Server relational database

STORY – Foundation – N-item Sort – Admin Set-up

AS A Business I WANT to define a system whereby people can directly compare N items in groups of 2 and store their responses SO THAT I can analyze their responses and calculate a bias-free ranking

GIVEN an administrator setting up the test AND no items currently exist in the system (e.g., stored in the database) WHEN the administrator goes to define the N items THEN the administrator is presented with the ability to enter any number of items, where an item is a word or phrase (text data) AND there is a way for the administrator to indicate when he/she is finished entering items (such as a “Finish” button) AND there is a way to cancel the task (such as a “Cancel” button)

GIVEN an administrator setting up the test AND some items already exist in the system (e.g., stored in the database) WHEN the administrator goes to define the N items THEN the administrator is presented with a noneditable list of existing items in the system

GIVEN an administrator entering items to define a test AND fewer than 2 items have been entered WHEN the administrator begins entering items THEN the “Finish” action is disabled

GIVEN an administrator entering items to define a test AND at least 2 items have been entered WHEN the administrator enters the 2nd item THEN the “Finish” action is enabled

STORY – Foundation – N-item Sort – User Taking Test

AS A User I WANT to take a test that directly compares N items in groups of 2 and stores my responses SO THAT I can understand my preferences

GIVEN some number of items configured (e.g. words) WHEN the user goes to the test THEN the user is assigned a unique test-session ID

GIVEN a user taking the test, with a unique test-session ID WHEN the user begins the test THEN the user is presented with two items (e.g. words) to choose from AND can select one, the other, or “I can’t decide” AND their choice is stored somewhere (e.g. written to a database) AND that choice is stored as one “win” for the item they chose and one “loss” for the item they didn’t choose, or a “tie” for both items if the user chose “I can’t decide”

GIVEN a user taking the test AND they have provided answers to every combination of A and B items WHEN the user would be shown a pair of items THEN instead they see something else (e.g. a “you’re finished!” message, or the results - TBD)

STORY – Basic Result Reporting

AS A Business I WANT the ability to view the ranked items for a given user SO THAT I can use that information for business decisions (e.g. talk about it with client)

GIVEN a user who has finished the ranking process WHEN I pull up their ranking results THEN I can see the items in ranking order, where the order is calculated based on 1 point for a win, 0 points for a tie and -1 point for a loss AND I can see the win-loss-tie record for each item

STORY – Progress Meter

AS A User I WANT to know how close to “complete” I am SO THAT I can have a sense of making progress

AS A Business I WANT to be able to display a progress indicator to the user SO THAT I can evaluate if the progress indicator causes people to complete more or less responses than without it

GIVEN N items AND M possible combinations AND X combinations have been compared AND Z combinations are unknown WHEN the progress indicator is shown THEN it shows a typical blue progress bar for X AND empty for Z
