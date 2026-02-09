# Лабораторная работа №9-10: Продвинутое ООП на Kotlin

## Описание
Лабораторная работа посвящена изучению продвинутых концепций объектно-ориентированного программирования в Kotlin. 

## Структура проекта
Проект содержит примеры реализации продвинутых механизмов ООП, а также пояснения к ключевымконцепциям. 

## Как запустить проект
1. Клонируйте репозиторий:
``` bash
git clone <https://github.com/karasidnez/Kotlin_Advanced_Lab_9_10_Timonin>
``` 
2. Откройте проект в IntelliJ IDEA. 
3. Запустите любой пример через контекстное меню или напрямую из `main`. 

## Интерфейсы

Это **контракт поведения.**

Содержит:
- функции для реализации;
- функции с default-реализацией;
- свойства без хранения (только декларации)

**Пример**

**Определяем интерфейс видео-плеера:**

```kotlin
interface VideoPlayable {
	fun play()
}
```
**Определяем интерфейс аудио-плеера:**
```kotlin
interface AudioPlayable {
	fun play()
}
```
Далее создадим класс, который будет реализовывать оба интефейса, и в нём переопределим метод для обоих интерфейсов:
```kotlin
class MediaPlayer : VideoPlayable, AudioPlayable {
	override fun play(){
  	println("Play audio and video")
  }
}
```

## Геттеры и сеттеры

**Геттеры (get) и сеттеры (set)** — специальные методы для доступа к свойствам класса, позволяющие контролировать чтение и запись значений.
### Особенности:
- Позволяют добавлять дополнительную логику при доступе к свойствам
- Обеспечивают валидацию данных
- Могут вычислять значения на лету
- В Kotlin реализованы автоматически, но могут быть кастомизированы

### Пример:

```kotlin
class Person {
    var age: Int = 0
        set(value) {
            if (value >= 0 && value <= 120) {
                field = value
            } else {
                println("Некорректный возраст")
            }
        }
        get() {
            println("Возраст прочитан: $field")
            return field
        }
    
    val isAdult: Boolean
        get() = age >= 18
}

fun main() {
    val person = Person()
    person.age = 25
    println(person.age)
    println("Совершеннолетний: ${person.isAdult}")
}
```

# Инкапсуляция

**Инкапсуляция** — это принцип ООП, который объединяет данные и методы, работающие с этими данными, в одной структуре (классе), скрывая детали реализации и предоставляя контролируемый доступ.

**Основные идеи:**
- Объединение данных (свойств) и поведения (методов) в одном классе
- Сокрытие внутреннего состояния объекта
- Предоставление публичного интерфейса для взаимодействия
- Защита данных от некорректного использования

## Пример реализации

```kotlin
class BankAccount {
    private var balance: Double = 0.0

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Внесено: $amount. Новый баланс: $balance")
        } else {
            println("Некорректная сумма")
        }
    }
    
    fun withdraw(amount: Double): Boolean {
        if (amount > 0 && amount <= balance) {
            balance -= amount
            println("Снято: $amount. Новый баланс: $balance")
            return true
        }
        println("Недостаточно средств или некорректная сумма")
        return false
    }
    
    fun getBalance(): Double {
        return balance
    }
}

fun main() {
    val account = BankAccount()
    
    account.deposit(1000.0)
    account.withdraw(500.0)
    
    println("Текущий баланс: ${account.getBalance()}")
}
```
# Data-классы

**Data-классы** — это специальные классы в Kotlin, предназначенные исключительно для хранения данных. Они автоматически генерируют стандартные методы (toString(), equals(), hashCode(), copy()) и предоставляют возможность деструктурирующего объявления.

## Основные характеристики

1. **Автоматическая генерация методов:**
    - `toString()` — строковое представление
    - `equals()` и `hashCode()` — сравнение объектов
    - `copy()` — создание копии с возможностью изменения отдельных свойств
    - `componentN()` функции — для деструктуризации

2. **Ограничения:**
    - Должны иметь хотя бы один параметр в конструкторе
    - Все параметры конструктора должны быть отмечены как `val` или `var`
    - Не могут быть `abstract`, `open`, `sealed` или `inner`

## Примеры использования

### Базовый пример data-класса

```kotlin
data class Person(
    val name: String,
    val age: Int,
    val email: String = "unknown@example.com"  
)

fun main() {
    val person1 = Person("Алексей", 30, "alex@example.com")
    val person2 = Person("Мария", 25)
    val person3 = Person("Алексей", 30, "alex@example.com")
    
    println(person1)  
    
    println(person1 == person3)  
    println(person1 == person2)  
    
    val person4 = person1.copy(name = "Александр")
    println(person4)  
    
    val (name, age, email) = person1
    println("Имя: $name, Возраст: $age")  
}
```
# Galaxy Outpost Manager

Учебный проект на Kotlin, демонстрирующий основы объектно-ориентированного программирования и архитектурные приёмы языка.

## Изученные темы и их применение

### Sealed-классы

Sealed-классы используются для представления ограниченного набора состояний или результатов, которые известны на этапе компиляции. Они позволяют:
- Гарантировать обработку всех возможных вариантов при использовании `when`
- Безопасно использовать конструкцию `when` без ветки `else`
- Удобно описывать состояния, события и результаты действий

```kotlin
sealed class ModuleResult {
    data class Success(val message: String) : ModuleResult()
    data class ResourceProduced(val resourceName: String, val amount: Int) : ModuleResult()
    data class NotEnoughResources(
        val resourceName: String, 
        val required: Int, 
        val available: Int
    ) : ModuleResult()
    data class Error(val reason: String) : ModuleResult()
    object Processing : ModuleResult() 
}

fun handleModuleResult(result: ModuleResult) {
    when (result) {
        is ModuleResult.Success -> println("Успех: ${result.message}")
        is ModuleResult.ResourceProduced -> {
            println("Произведено ${result.amount} единиц ${result.resourceName}")
        }
        is ModuleResult.NotEnoughResources -> {
            println("Недостаточно ${result.resourceName}. Нужно: ${result.required}, есть: ${result.available}")
        }
        is ModuleResult.Error -> println("Ошибка: ${result.reason}")
        ModuleResult.Processing -> println("Модуль в процессе работы")
    }
}
```
### Object в Kotlin
object — это специальная конструкция Kotlin, которая создаёт единственный экземпляр класса (Singleton).Особенности:
- создаётся при первом обращении;
- существует в одном экземпляре;
- не имеет конструктора. Пример: глобальный логгер
```Kotlin
object Logger {
private var counter = 0
fun log(message: String) {
counter++
println("[$counter] $message")
}
}
```
Использование:
- Logger.log("Инициализация системы")
- Logger.log("Модуль запущен")
- object удобно использовать для:
- логгеров;
- конфигураций;
- состояний без данных в sealed-классах;
- утилитарных классов.


## Автор
[Тимонин Иван Витальевич]

## Лицензия
Проект создан в учебных целях