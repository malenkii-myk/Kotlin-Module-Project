import java.util.Scanner

class InputableMenu() {
    fun userInputStr( field: String ): String {
        while (true) {
            println("Укажите ${field}:")
            val cmd = Scanner(System.`in`).nextLine().trim()
            if (cmd == "") {
                println("Ошибка: поле \"${field}\" не может быть пустым!")
                continue
            }
            return cmd
        }
    }
}