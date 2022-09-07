// Type is inferred
let sales = 123_456_789;

let course: string = 'TypeScript';
let isPublished: boolean = true;

let level: any;
level = 2;
level = 'two'
level = undefined;

// Parameter type should be explicitly defined
function render(document: any) {
	console.log(document);
}

// Array
let numbers: number[] = [1, 2, 3];

// Tuple
let user: [number, string] = [1, 'Art']
user[1] = 'Zen'
user.push(3) // Note: no TypeScript error. Perhaps a hole...
console.log(user)

// Enum
const enum Size {
	SMALL = 's', MEDIUM = 'm', LARGE = 'l'
}
console.log(Size.SMALL)
let mySize: Size = Size.LARGE
console.log(mySize)