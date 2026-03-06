Feature: Article Create Update and Delete

#Login Verification
Scenario: User Login
Given User is on login Page
When User enters "shivanshrag@gmail.com" and "shivansh16"
Then User should be on Home page

#Add Article
Scenario Outline: Create Article
Given User should be Article Page
When User Create Article "<title>" and "<desc>" and "<body>" and "<tag>"
Then Article must be Created
Examples:
| title | desc | body | tag |
| SHIVANSH888 | SR | NONE | TAG |


#Update article 
Scenario: Update an Article
Given Article must be Created
When User Update an Article
| oldtitle | newtitle | desc | body | tag |
| SHIVANSH888 | SHIV888 | SR | NONE | TAG |
Then Article Should be Updated

#Delete Article
Scenario: Delete an Article
Given Article Should be Updated
When User Delete an Article
| title |
| SHIV888 |
Then Article Should be Deleted