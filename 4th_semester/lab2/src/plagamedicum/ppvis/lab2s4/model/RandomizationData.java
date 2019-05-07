package plagamedicum.ppvis.lab2s4.model;

import java.util.List;
import java.util.Random;

public class RandomizationData {
    private static String[] surnameList = {
            "Тарашкевіч", "Торвальдс", "Платонаў", "Набокаў", "Караткевіч", "Кармак", "Распуцін", "Жукаў", "Алексіевіч", "Лукашэнка", "Шаўчэнка", "Клюеў", "Дунін-Марцінкевіч", "Раманаў", "Каліноўскі", "Залізняк", "Кнарозаў", "Іваноў", "Шышкоў", "Шагал", "Малевіч", "Качаеў", "Камадзей", "Гандзі", "Ленін", "Гарбачоў", "Гагарын", "Леонтьеў", "Лавкрафт", "Фон Бісмарк", "Манчан", "Колас", "Купала", "Лермантаў", "Блок", "Ламаносаў", "Міхалок", "Энштэйн", "Эйлер", "Бернулі", "Буль", "Лем", "Цой", "Кім", "Маркс", "Дэн", "Мао", "Ван Бюрэн", "Махно", "Тайджуддзін", "Карым", "Макконал", "Гаўс", "Радзівіл", "Рыбнікаў", "Мандэльштам", "Каралёў"
                    },
                            nameList = {
            "Браніслаў", "Лінус", "Андрэй", "Аляксей", "Уладзімір", "Гедемін", "Джон", "Мікітко", "Аляксандр", "Генадзь", "Максім", "Яраслаў", "Павел", "Кастусь", "Паніні", "Юрый", "Іван", "Марк", "Казімір", "Ульфрык", "Герберт", "Стэфан", "Якуб", "Янка", "Міхаіл", "Данііл", "Дмітрый", "Станіслаў", "Сяргей", "Віктар", "Чен Ір", "Ір Сэн", "Чен Ын", "Нурсултан", "Мустафа", "Карл", "Сяопін", "Цзэдун", "Отто", "Адольф", "Нікола", "Армен", "Адальхэйдюр", "Соўльвэйг", "Фрыц", "Ганс", "Нестар", "Сціў", "Глеб", "Усеслаў", "Восіп"
                    },
                            patronymList = {
            "Адамавіч", "Платонавіч", "Сямёнавіч", "", "сын Аляксееў", "Рыгоравіч", "Іванавіч", "Аляксандравіч", "Мікалаевіч", "Браніслававіч", "Валянцінавіч", "Вітаўтавіч", "Валер'янавіч", "Ігаравіч", "Демідавіч", "Анатольевіч", "Оўлаўсан", "Хадльдоўрсан", "Ёўнатансдаўтцір", "Ільясавіч", "Мустафавіч", "Стэфанавіч", "Юр'евіч", "Армені", "Эстрыдсан", "Торстэнсан", "Антыпойка", "Юханцюцяр", "Мійтрэй", "Васі", "Пека", "Салім-аглы", "Іньігэса", "ібн Мухаммад", "бэн Давід", "бар Йохай", "фі дэ Геральд", "Карлавіч"
                    },
                            examNameList = {
            "Беларуская мова", "Матэматычны аналіз", "Асновы акцэнталогіі", "Філасофія", "Фізічная культура і здароўе", "Тапалогія", "Тэорыя імавернасцей", "Гісторыя Ўсходняга Тымара", "ППуІС", "МАІС", "МРЗуІС", "ЫЫЫЫ", "Замежная літаратура", "Татарская мова", "Царкоўна-славянская мова", "Фарсі", "Хіндзі", "Кітайская мова", "ВОВСНВМВ", "Электронныя прылады", "Фізіка", "Сальфеджыа", "Сусветная гісторыя", "Грамадазнаўства", "Паліталогія", "Выешал", "Эсперанта", "Асновы канструявання моў", "Архітэктура", "Хімія", "Ангельская мова", "Венгерская мова", "Гішпанская мова", "Польская мова", "ТІІТ", "Тэалогія", "Асновы мовазнаўства", "Расейская мова", "Геаграфія", "Труд", "Алгебра", "Чарчэнне", "Літоўская мова", "Валапюк", "Інтэрлінгва", "Ідыш", "Іўрыт", "Гісторыя Кіпра", "Кяхцінская мова", "Русенорск", "Французская літаратура", "Літаратуразнаўства", "Біялогія", "Анатомія", "Заалогія", "Археалогія", "Лексікаграфія", "Фаналогія", "Краіназнаўства", "Гісторыя Лівана", "Арабская дыялекталогія", "Мова майя", "Старажытна-руская акцэнталогія", "Тыбецкая мова", "Праславянская мова", "Картаграфія", "Гісторыя архітэктуры", "Прамысловая інжэнерыя", "Оптыка", "Астраномія", "Батаніка", "Гісторыя мастацтваў", "Мастацтвазнаўства", "Гісторыя кіно", "Гісторыя музыкі", "Каліграфія", "Маркетынг", "Тувінская мова", "Гісторыя Тувы", "Гісторыя Мары Эм", "Ятвяжскакя мова", "Латынь", "Эканоміка"
                    };

    public static String reqSurname(){
        return surnameList[new Random().nextInt(surnameList.length)];
    }

    public static String reqName(){
        return nameList[new Random().nextInt(nameList.length)];
    }

    public static String reqPatronym(){
        return patronymList[new Random().nextInt(patronymList.length)];
    }

    public static String reqExamName(){
        return examNameList[new Random().nextInt(examNameList.length)];
    }
}