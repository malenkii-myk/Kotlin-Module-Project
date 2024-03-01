import java.util.Scanner

class App() {

    var archiveList: MutableList<ArchiveElement> = mutableListOf()
    var noticeList: MutableList<NoticeElement> = mutableListOf()

    fun init() {
        println("Приложение «Заметки» приветсвует Вас!")
        menuArchiveList()
        println("Работа программы завершена")
    }

    fun menuArchiveList(){
        val menu = SelectableMenu()
        menu.setOptions(
            "Архивы",
            listOf(MenuElement("Создать новый архив", {this.menuCreateNewArchive()})) +
                    this.archiveList.mapIndexed{ index, el ->
                        MenuElement(el.name, { this.menuNoticeList(index) })
                    } +
                    listOf(MenuElement("Выход", { menu.exit() }))
        )
        menu.run()
    }

    fun menuCreateNewArchive(){
        val menu = InputableMenu()
        println("Создание нового архива")
        val name = menu.userInputStr("название")
        this.archiveList.add(ArchiveElement(name))
        println("Архив \"${name}\" успешно создан.")
        this.menuArchiveList()
    }

    fun menuNoticeList( archiveId: Int) {
        val menu = SelectableMenu()
        menu.setOptions(
            "Архив: "+this.archiveList[archiveId].name,
            listOf(MenuElement("Создать новую заметку", {this.menuCreateNewNotice(archiveId)})) +
                    this.noticeList.filter { el -> el.archiveId == archiveId }.mapIndexed{ index, el ->
                        MenuElement(el.name, { this.menuNoticeInfo(el) })
                    } +
                    listOf(MenuElement("Назад", { this.menuArchiveList() }))
        )
        menu.run()
    }

    fun menuCreateNewNotice( archiveId: Int) {
        val menu = InputableMenu()
        println("Создание новой заметки")
        val name = menu.userInputStr("название")
        val content = menu.userInputStr("содержание")
        this.noticeList.add(NoticeElement(name, archiveId, content))
        println("Заметка \"${name}\" успешно созданв.")
        this.menuNoticeList(archiveId)
    }

    fun menuNoticeInfo( notice: NoticeElement ) {
        println("${archiveList[notice.archiveId].name} / Заметка")
        println("Название:   ${notice.name}")
        println("Содержание: ${notice.content}")
        println()
        println("Для возврата в меню нажмите ENTER...")
        Scanner(System.`in`).nextLine()
        this.menuNoticeList(notice.archiveId)
    }
}