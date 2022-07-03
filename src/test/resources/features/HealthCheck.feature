@ui @healthcheck
Feature: E-commerce Project test cases

  Background: Navigation to the URL
    Given User navigated to the home application url

  @test1
  Scenario: User is able to Open the browser, validate the Url
    Then Page Url should be "http://automationpractice.com/index.php"

  @test2
  Scenario: User is able to Open the browser, check the logo visibility 
   Then page should contain logo with desired width as 350 and height as 99
   
  @test3
  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    When User Search for product "T-shirt"
    Then Search Result page is displayed with text "T-shirt"

  @test4
  Scenario Outline: Application product main category list validation
    Then main product categories count should be 3
    When User Search for product categories "<product_list>"
    And display the text of three categories

    Examples: 
      | product_list |
      | women        |
      | dresses      |
      | T-shirts     |
      
  @test5
  Scenario: user is able to open the browser,and can validate social media link
    When user search for twitter link from footer section of the landing page
    Then Product Description is displayed in new tab with title "seleniumfrmwrk"
    And the twitter account name should be "Selenium Framework"