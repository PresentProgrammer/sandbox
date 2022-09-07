type Draggable = {
	drag: () => void,
}

type Resizable = {
	resize: () => void,
}

type UIWidget = Draggable & Resizable

const textBox: UIWidget = {
	drag: () => {
		console.log('dragged')
	},
	resize: () => {
		console.log('resized')
	},
}

textBox.drag()
textBox.resize()