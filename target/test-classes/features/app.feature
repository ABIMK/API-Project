Feature: Add product to cart
Description : Verify product flow.

Scenario: Add product.
	Given Products are listed
	When Select the product
	And Navigate to product page
	And Click Add to cart button
	Then Product is added to cart


