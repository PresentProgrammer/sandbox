function calculateTax(income: number, taxYear?: number): number {
	if (taxYear && taxYear < 2022) {
		return income * 0.2;
	} else {
		return income * 0.23;
	}
}
console.log(calculateTax(10_000))
console.log(calculateTax(10_000, 2017))