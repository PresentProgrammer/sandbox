type Employee = {
	id: number,
	name?: string,
	retire: (date: Date) => void,
}
let employee: Employee = {
	id: 1,
	retire: (date: Date): void => {
		console.log(`Retire on ${date}`)
	},
}
console.log(employee)
employee.retire(new Date())
console.log(employee?.name?.charAt(0))