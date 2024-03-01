import java.util.Scanner

class SelectableMenu() {
    var title: String = ""
    var elements: List<MenuElement> = listOf()
    var isInput: Boolean = true
    fun setOptions(
        title: String,
        elements: List<MenuElement>
    ) {
        this.title = title
        this.elements = elements
    }
    fun draw(){
        println(this.title)
        for (i in elements.indices) {
            println("${i} - ${elements[i].title}")
        }
        println("Введите номер (0..${elements.size-1}):")
    }

    fun run(){
        while (this.isInput) {
            this.draw()
            val cmd = this.userInputInt()
            if (cmd < 0 || cmd > elements.size-1) {
                println("Ошибка: следует вводить число в диапазоне 0..${elements.size-1}")
                continue
            }
            for (i in elements.indices) {
                if (i == cmd) {
                    this.exit()
                    elements[i].callback()
                }
            }
        }
    }
    fun exit(){
        this.isInput = false
    }
    fun userInputInt(): Int {
        val cmd: String = Scanner(System.`in`).nextLine()
        try {
            return cmd.toInt()
        } catch (e: Exception) {
            return -1
        }
    }
}