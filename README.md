# Втора лабораториска вежба по Софтверско инженерство

## Васко Павиќ, бр. на индекс 195096

###  Control Flow Graph

![cfg](https://github.com/vaskopavic/SI_2023_lab2_195096/assets/116276295/127fdee1-7553-45b4-bba1-f72e49be06e8)

### Цикломатска комплексност:

    Цикломатската комплексност е дефинирана со M = E - N + 2, каде што E е број на рабови, N е број на јазли.
    Во овој случај сложеноста е 31 - 22 + 2 = 11

### Тест случаи според критериумот Every Branch

    1. user = null, allUsers = X
    2. user.username = null, user.pass = "12345", user.email = "email@gmail.com", allUsers = [diff, diff, same]
    3. user.username = "username", user.email = "none",  user.pass = "123456789", allUsers = [X]
    4. user.password = "123 654321", allUsers = []
    5. user.password = "1234567#!", user.email = "none", user.username = "username", allUsers = []

### Тест случаи според критериумот Multiple Condition

    1. User = null
    2. User != null, user.password = null
    3. User != null, user.password != null, user.email = null
    4. User != null, user.password != null, user.email != null

### Опис на Unit тестови
CASE: testEveryBranch()
  
    1. Прв тест случај (user == null, allUsers = X) - проверува случај кога user е null и завршува со фрлање на исклучок RuntimeException.
    Ги покрива гранките: 1-2, 2-18.
    2. Втор тест случај (user.username == null, user.pass == 12345, user.email == email@123.com, allUsers = [diff, diff, same]) - проверува условите со null user.username, невалиден user.pass (пократок од 8 карактери), валиден user.email и листа со корисници.
    Ги покрива гранките: 1-3, 3-4, 4-5, 5-6.1, 6.1-6.2, (6.2-7, 7-9, 9-6.3, 6.3-6.2), 6.2-7, 7-8, 8-9, 9-10, 10-6.3, 6.3-6.2, 6.2-11, 11-12, 12-18.
    3. Трет тест случај (user.username = username, user.email = NONE, user.pass = 123456789, allUsers = [randomUser]) - проверува условите со валиден user.username, невалиден user.email, валиден user.pass и листа со корисници.
    Ги покрива гранките: 1-3, 3-5, 5-11, 11-13, 13-14.1, 14.1-14.2, (14.2-15, 15-14.3, 14.3-14.2), 14.2-17, 17-18.
    4. Четврт тест случај (user.password = 123 654321 allUsers = []) - проверува условите со невалиден user.password (содржи празно место) и празна листа со корисници.
    Ги покрива гранките: 1-3, 3-5, 5-11, 11-13, 13-17, 17-18.
    5. Петти тест случај (user.password = 1234567#!, user.email = "none", user.username = username, allUsers = []) - проверува условите со валиден user.password (без празни места и со специјални карактери), невалиден user.email, валиден user.username и празна листа со корисници.
    Ги покрива гранките: 1-3, 3-5, 5-11, 11-13, 13-14.1, 14.1-14.2, (14.2-15, 15-14.3, 14.3-14.2), 14.2-15, 15-16, 16-18.

CASE: testMultipleCondition()

    1. Прв тест случај (T X X, user == null, X X) - проверува случај кога user е null.
    Очекува да се фрли на исклучок RuntimeException.
    2. Втор тест случај (F T X; user != null, user.password == null) - проверува случај кога user не е null, но user.password е null.
    Очекува да се фрли исклучок RuntimeException.
    3. Трет тест случај (F F T; user != null, user.password != null && user.email == null) - проверува случај кога user не е null, user.password не е null, но user.email е null.
    Очекува да се фрли исклучок RuntimeException.
    4. Четврт тест случај (F F F; user != null, user.password != null && user.email != null) - проверува случај кога сите user, user.password и user.email се различни од null.
    Очекува да се врати на вредноста false според тестот.
