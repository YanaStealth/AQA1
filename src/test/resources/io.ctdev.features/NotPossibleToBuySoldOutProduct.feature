Feature: Buying sold out product

  Scenario Outline: It is not possible to buy sold out goods // TC name
    Given user opens main page
    When user closes Dialogue Popup
    And clicks On Account Button
    And clicks On Login Button
    And user enters login "<login>" and password "<password>"
    And user clicks on sbmit button
    And user "<login>" should be logged to application
    And user refreshes current page
    And user clicks on Proceed To Next Shop Page button
    When user put sold out product to basket
    And opens the basket
    Then total amount of the basket should be "<cartTotalAmount>"
    Examples:
      | login             | password | expectedUser      | cartTotalAmount|
      | "yana4@gmail.com" | "qQ2$4"  | "yana4@gmail.com" | "Total Price: 0¤"|




