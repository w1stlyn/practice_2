```markdown
# Практическое занятие 2: Основные конструкции языка Java

**Курс:** Современные технологии программирования  
**Условие задания:** [aliebraheem-fun.github.io/Modern-Programming-Technologies/#/practice2](https://aliebraheem-fun.github.io/Modern-Programming-Technologies/#/practice2)  
**Исходный репозиторий:** [github.com/Mohanad0101/practice2](https://github.com/Mohanad0101/practice2)

---

## Требования

| Программа | Версия | Ссылка для скачивания |
| :--- | :--- | :--- |
| **JDK** | 21 или новее | [oracle.com/java/technologies/downloads](https://www.oracle.com/java/technologies/downloads/) |
| **Git** | любая актуальная | [git-scm.com/downloads](https://git-scm.com/downloads/) |
| **IntelliJ IDEA** | Community Edition | [jetbrains.com/idea/download](https://www.jetbrains.com/idea/download/) |
| **Аккаунт GitHub** | — | [github.com](https://github.com) |

---

## Шаг 1. Форк репозитория

1.  Откройте в браузере: [https://github.com/Mohanad0101/practice2](https://github.com/Mohanad0101/practice2)
2.  В правом верхнем углу нажмите кнопку **Fork**.
3.  Убедитесь, что в качестве владельца (`Owner`) выбран ваш аккаунт.
4.  Нажмите **Create fork**.
5.  Дождитесь создания. Вы окажетесь на странице `https://github.com/ВАШ_ЛОГИН/practice2`, где в заголовке должно быть указано `forked from Mohanad0101/practice2`.

---

## Шаг 2. Настройка SSH-ключа (один раз на компьютер)

> SSH позволяет IntelliJ IDEA отправлять ваш код на GitHub без ввода пароля при каждом `push`.

### 2.1. Откройте терминал
-   **Windows:** откройте **Git Bash** (устанавливается вместе с Git).
-   **macOS / Linux:** откройте **Терминал**.

### 2.2. Проверьте, есть ли уже ключ
Выполните команду:
```bash
ls -al ~/.ssh
```
Если в выводе есть файл `id_ed25519.pub` или `id_rsa.pub`, ключ уже существует. Переходите к **шагу 2.4**.

### 2.3. Создайте новый ключ
```bash
ssh-keygen -t ed25519 -C "ваш_email@example.com"
```
Нажмите **Enter** три раза, чтобы принять путь по умолчанию и не устанавливать пароль.

### 2.4. Скопируйте публичный ключ
```bash
cat ~/.ssh/id_ed25519.pub
```
Скопируйте весь вывод этой команды.

### 2.5. Добавьте ключ в GitHub
1.  Откройте: [https://github.com/settings/keys](https://github.com/settings/keys)
2.  Нажмите **New SSH key**.
3.  **Title:** введите имя компьютера (например, «Ноутбук университет»).
4.  **Key type:** `Authentication Key`.
5.  В поле **Key** вставьте скопированный ключ.
6.  Нажмите **Add SSH key**.

### 2.6. Проверьте соединение
```bash
ssh -T git@github.com
```
> Ожидаемый ответ: `Hi ВАШ_ЛОГИН! You've successfully authenticated, but GitHub does not provide shell access.`

---

## Шаг 3. Подключение GitHub-аккаунта в IntelliJ IDEA

1.  **Проверьте плагины:**
    -   `File` → `Settings` → `Plugins` → вкладка `Installed`.
    -   Найдите `Git` и `GitHub` — оба должны быть активны.
2.  **Если плагины не установлены:**
    -   Перейдите на вкладку `Marketplace`.
    -   Введите «GitHub» → **Install** → перезапустите IntelliJ IDEA.
3.  **Добавьте аккаунт GitHub:**
    -   `File` → `Settings` → `Version Control` → `GitHub`.
    -   Нажмите `+` → `Log In via GitHub...`
    -   В открывшемся браузере войдите в свой аккаунт и разрешите доступ.
    -   Вернитесь в IntelliJ — аккаунт появится в списке. Нажмите **OK**.

---

## Шаг 4. Клонирование форка в IntelliJ IDEA

1.  На стартовом экране IntelliJ нажмите **Get from VCS**.
    -   (Если проект уже открыт: `File` → `New` → `Project from Version Control`)
2.  В поле **URL** введите SSH-адрес вашего форка:
    ```
    git@github.com:ВАШ_ЛОГИН/practice2.git
    ```
    > Адрес можно взять на странице вашего форка в браузере → кнопка **Code** → вкладка **SSH**.
3.  В поле **Directory** выберите удобную папку на компьютере.
4.  Нажмите **Clone**.
5.  На вопрос «Trust and Open Project?» → нажмите **Trust Project**.

> **Примечание:** Проект автоматически откроется с настройками Java 21, так как они сохранены в файле `practice2.iml`. Если JDK не найден, укажите путь к нему вручную: `File` → `Project Structure` → `Project` → `SDK` → `Add SDK` → `JDK...`

---

## Шаг 5. Выполнение заданий

#### Рабочий цикл для каждого задания:

1.  **ОТКРОЙТЕ** файл задания (например, `.java`-файл в панели `Project`).
2.  **ПРОЧИТАЙТЕ** заголовочный Javadoc — там содержится теория и ожидаемый вывод.
3.  **РЕАЛИЗУЙТЕ** методы, следуя подсказкам. Ищите маркеры:
    ```java
    // ▼ ВАШ КОД ЗДЕСЬ ▼
    ...
    // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    ```
4.  **ЗАПУСТИТЕ** код, нажав ▶ рядом с методом `main`.
5.  **СРАВНИТЕ** ваш вывод в панели «Run» с ожидаемым выводом из Javadoc.
6.  **ЗАПОЛНИТЕ** письменные ответы (если требуются) в файлах `answers/*.md`.
7.  **ЗАФИКСИРУЙТЕ** прогресс (см. Шаг 6).

---

## Шаг 6. Сохранение прогресса (после каждого задания)

1.  Нажмите `Ctrl+K` (Windows/Linux) или `Cmd+K` (macOS), чтобы открыть окно коммита.
2.  Отметьте галочкой изменённые файлы.
3.  Напишите осмысленное сообщение коммита, например:
    ```
    Задание 1.1: реализован класс BankAccount
    ```
4.  Нажмите **Commit and Push...** (не просто «Commit»).
5.  В диалоге `Push` снова нажмите **Push**.
6.  Проверьте, что новый коммит появился в вашем репозитории на GitHub.

---

## Что сдавать

-   **Реализованные методы** во всех файлах `.java` в папках `part1/`–`part8/` и `company/`.
-   **Заполненные файлы ответов:**
    -   `answers/task1_2_access_table.md`
    -   `answers/task2_4_abstract_vs_interface.md`
    -   `answers/part9_jshell.md`
    -   `answers/part10_questions.md`
-   Все изменения должны быть запушены в ваш форк на GitHub. Преподаватель проверяет работу по адресу: `https://github.com/ВАШ_ЛОГИН/practice2`.

---

## Структура репозитория

```
practice2/
├── README.md
├── .gitignore
├── practice2.iml
├── docs/
│   ├── ЗАПУСК_КЛАССОВ.md           ← полные имена классов для Run
│   ├── ПРОВЕРКА_ПРЕПОДАВАТЕЛЮ.md  ← javac, upstream diff
│   └── slides/                   ← веб-презентация (ООП + идеи практики)
├── answers/
│   ├── task1_2_access_table.md
│   ├── task2_4_abstract_vs_interface.md
│   ├── part9_jshell.md
│   └── part10_questions.md
├── company/                    ← задание 1.2
├── part1/
│   ├── part1_1/                ← задание 1.1
│   └── part1_3/                ← задание 1.3
├── part2/
│   ├── part2_1/                ← задание 2.1
... и так далее ...
└── part8/
    └── part8_1/                ← задание 8.1
```
```
