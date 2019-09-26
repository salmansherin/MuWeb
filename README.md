# MuWeb
A prototype tool that support mutation analysis for web applications. Currently, it supports mutation operators for HTML, JavaScript, JQuery, SQL and PHP. The detailed list of supported operators are given below:

List of supported Mutation operators

HTML
•	Simple link replacement
•	Simple link deletion
•	Form link replacement
•	Transfer mode replacement
•	Hidden Form field replacement
•	Hidden form field deletion
•	Server-side include deletion
•	Server-side include replacement

JavaScript
•	Adding/Removing the var keyword
•	Removing the global search flag from replace
•	Removing the integer base argument from parseInt
•	Changing setTimeout function
•	Replacing undefined with null
•	Removing this keyword
•	Replacing (function()!==false) by (function())

JQuery
•	Swap {#} and {.} sign in selector.
•	Remove {$} sign that returns a jquery object.
•	Change the name of the property or class or element in the following methods: addClass, removeClass, removeAttr, prop, CSS, remove and detach.

SQL
•	Omitted distinct in select
•	One join with the wrong type
•	Two joins with the wrong type
•	Use a wrong SQL join
•	Join incorrect tables
•	Wrong columns in order by
•	Order by omitted
•	Unnecessary IS NULL
•	Omitted IS NULL
•	Wrong columns in the select list

PHP
•	Arithmetic
•	Boolean
•	Conditional boundary
•	Conditional Negation
•	If statement
•	Increment
•	Number
•	Return value

