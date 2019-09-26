# MuWeb
A prototype tool that support mutation analysis for web applications. Currently, it supports mutation operators for HTML, JavaScript, JQuery, SQL and PHP. The detailed list of supported operators are given below:

List of supported Mutation operators

**HTML**<br/>
Simple link replacement<br/>
Simple link deletion<br/>
Form link replacement<br/>
Transfer mode replacement<br/>
Hidden Form field replacement<br/>
Hidden form field deletion<br/>
Server-side include deletion<br/>
Server-side include replacement
<br/>
**JavaScript**<br/>
Adding/Removing the var keyword<br/>
Removing the global search flag from replace<br/>
Removing the integer base argument from parseInt<br/>
Changing setTimeout function<br/>
Replacing undefined with null<br/>
Removing this keyword<br/>
Replacing (function()!==false) by (function())
<br/>
**JQuery**<br/>
Swap {#} and {.} sign in selector<br/>
Remove {$} sign that returns a jquery object<br/>
Change the name of the property or class or element in the following methods: addClass, removeClass, removeAttr, prop, CSS, remove and detach
<br/>
**SQL**<br/>
Omitted distinct in select<br/>
One join with the wrong type<br/>
Two joins with the wrong type<br/>
Use a wrong SQL join<br/>
Join incorrect tables<br/>
Wrong columns in order by<br/>
Order by omitted<br/>
Unnecessary IS NULL<br/>
Omitted IS NULL<br/>
Wrong columns in the select list
<br/>
**PHP**<br/>
Arithmetic<br/>
Boolean<br/>
Conditional boundary<br/>
Conditional Negation<br/>
If statement<br/>
Increment Number<br/>
Return value<br/>

