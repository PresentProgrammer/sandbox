// https://github.com/Microsoft/TypeScript/issues/13002

type A = {
	readonly id: number
}

type B = {
	id: number
}

const a: A = { id: 1 }
const b: B = a
b.id = 2
console.log(a)