# JavaScript Basics sandbox

## Will need to add the following notes

### jQuery

* children() to target an element's child elements.
* siblings() to target elements adjacent to an element.
* parent() to target an element's parent.
* closest() travels up the DOM tree from the current element to target the closest element with a given selector.
* next() to target the element immediately following the selected element.
* prev() to target the element that is immediately preceding the selected element.
* find() to target descendant elements by some selector, ie- class, id, tag etc.

### Forms

<form> element's attributes:
* name - needed to address the form (document.<formName>);
* action - determines where the form is submitted;
* method - determines how the info is submitted;
* target - determines the frame to which the response to the submitted form is loaded.

You can reference form-corresponding objects (HtmlFormElement, Form is used further) through document.<formName> or document.forms collection.
Note: Form has .submit() method, that does not trigger submit event on the Form object.
Form's reset() makes entered values to be changed to defaults or cleared completely.

#### HTML elements in forms
Commonly found form elements are the following:
* Text box - <input type="text"/>
* Password box - <input type="password"/>
* Text area - <textarea></textarea>
* Check boxes - <input type="checkbox"/>
* Radio buttons - <input type="radio"/>
* Drop down list - <select><option></option></select>
* List box - <select size="4"><option></option></select>
* Button - <input type="button"/>
* Submit button - <input type="submit"/>
* Reset button - <input type="reset"/>