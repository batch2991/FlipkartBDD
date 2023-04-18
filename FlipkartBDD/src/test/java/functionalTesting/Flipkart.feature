@Flipkart
Feature: To verify the product details

@title
Scenario: verify the title and heading
Given user is on flipkart home page
When enter any product name and search
Then title and heading should have the product name

@sorting
Scenario: verify the prices are in sorting order
Given user is on flipkart home page
When enter any product name and search
And click prices low to high
Then all the products are sorted on price

@productdetails
Scenario Outline: verify the product name and price
Given user is on flipkart home page
When enter any product name and search
And click on any one of the product<index>
Then name of the product and price in both page are same
Examples:
|index|
|5|