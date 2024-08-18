import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(textModifier());
        }

    public static String textModifier(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите текст: ");
        // 1. Пользователь вводит текст одной строкой и нажимает “enter”.
        String userText = sc.nextLine();
        sc.close();
        //2. В тексте могут присутствовать различные специальные символы, которые надо обрабатывать:
        /*
        a. Если в тексте между словами присутствует несколько пробелов подряд, надо оставить только один из них.
        Для реализации этого подпункта нельзя пользоваться методами replace() и replaceAll().
         */
        userText = deleteSpace(userText);
        /*
        b. Если в тексте присутствует знак минус (-), это значит, что символ слева от этого знака надо поменять местами с символом, который стоит справа от этого знака.
        Обратите внимание, что символом может быть не только буква, но и цифра или любой другой знак/символ, в том числе символ пробела.
         После смены символов местами, знак минус (-) надо удалить из строки результата.
         */
        userText = swapMinus(userText);
        /*
        c. Если в тексте присутствует знак плюс (+), вам необходимо заменить его на восклицательный знак (!).
         */
        userText = userText.replace("+", "!");
        /*
        d. В тексте могут присутствовать цифры (от 0 до 9).
        Вам необходимо посчитать их сумму и удалить из текста.
        Сумму цифр вам нужно добавить в конец результирующей строки через пробел (пробел должен стоять перед суммой цифр).
        Если цифр в тексте не было - "0" (ноль) в конце строки выводить не нужно.
         */
        userText = countSum(userText);

        return userText;
    }

    public static String deleteSpace(String userText){
        StringBuffer changedText = new StringBuffer();
        boolean isSpace = false;
        //Получаем массив элементов из текста пользователя
        char[] userTextChar = userText.toCharArray();
        //Проходимся по массиву из символов
        for(int i=0; i<userTextChar.length; i++){
            if(userTextChar[i] == ' '){ // Если символом является пробел тогда дальше
                if(!isSpace) { //Если предыдущий символ был пробелом тогда не ставим пробел
                    changedText.append(' ');
                    isSpace = true;
                    }
                } else { //Если не было пробела, тогда вставляем букву в конечный текст
                    changedText.append(userTextChar[i]);
                    isSpace = false;
                }
            }
        //Преобразовываем текст в тип String и удаляем пробелы по бокам
        return changedText.toString().trim();
        }

        public static String swapMinus(String userText){
            StringBuffer changedText = new StringBuffer();
            char[] userTextChar = userText.toCharArray();

            for(int i = 0; i < userTextChar.length; i++){
                char indexTemp = userTextChar[i];
                if(indexTemp == '-'){
                    if(i > 0 && i < userTextChar.length - 1){ // если знак минус не стоит в конце или начале строки, тогда дальше
                        changedText.setCharAt(changedText.length() - 1, userTextChar[i+1]); //Берем знак справа от минуса
                        changedText.append(userTextChar[i-1]); //вставляем символ слева
                        i++;// пропуск следующего символа(т.к. уже проверяли его выше)
                    }
                } else {
                    changedText.append(indexTemp); //Вставляем букву в конечный текст
                }
            }
            return changedText.toString();
        }
        public static String countSum(String userText){
            StringBuffer changedText = new StringBuffer();
            char[] userTextChar = userText.toCharArray();
            int sum = 0; //переменная для подсчета суммы цифр из текста

            for(int i=0; i<userTextChar.length; i++){
                char num = userTextChar[i];
                if(Character.isDigit(num)){ //Если символ является цифрой, тогда дальше
                    sum += Character.getNumericValue(num); //прибавляем в сумму значение символа
                } else {
                    changedText.append(num);
                }
            }
            if(sum > 0){ //Если цифр не было в тексте, тогда не выводим сумму в конце текста
                changedText.append(" ").append(sum);
            }

            return changedText.toString();
        }
    }

