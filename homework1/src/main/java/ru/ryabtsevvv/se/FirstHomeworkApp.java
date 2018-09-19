package ru.ryabtsevvv.se;

/**
 * Основной класс приложения.
 */
public class FirstHomeworkApp {

    public static void main(String[] args) {
        int barrierComplexities[] = { 5, 10, 15, 20, 30, 35 };
        Course c = new Course( barrierComplexities ); // Создаем полосу препятствий

        int membersLevels[] = { 10, 20, 25, 40, 5 };

        // Создаем команду
        String name = "Loosers";
        Team team;
        if( membersLevels.length > Team.MAX_TEAM_SIZE) {
            System.out.println( "Team too large, get only first " + Team.MAX_TEAM_SIZE + " players.");
            int newMemberLevels[] = new int[Team.MAX_TEAM_SIZE];
            System.arraycopy( membersLevels, 0, newMemberLevels, 0, Team.MAX_TEAM_SIZE );
            team = new Team( name, newMemberLevels );
        }
        else {
            team = new Team( name , membersLevels );
        }

        c.doIt(team);               // Просим команду пройти полосу
        team.showResults();         // Показываем результаты
    }

}
