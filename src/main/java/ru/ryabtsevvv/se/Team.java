package ru.ryabtsevvv.se;

public class Team {

    public static final int MAX_TEAM_SIZE = 4;
    private String name;
    private TeamMember[] members;

    public Team(String name, int[] membersLevel) {
        this.name = name;
        members = new TeamMember[ membersLevel.length ];
        for( int i = 0; i < membersLevel.length; ++i ) {
            members[i] = new TeamMember( membersLevel[ i ] );
        }
    }

    public TeamMember getMember( int index ) {
        return members[ index ];
    }


    public void showResults() {
        int succeeded = 0;

        for( int i = 0; i < members.length; ++i) {
            if( members[ i ].isSucceeded() ) {
                ++succeeded;
            }
        }

        System.out.println( "Team \"" + name + "\" results: ");
        System.out.println( "Overall members: " + members.length );
        System.out.println( "Members, who passed the course: " + succeeded );
        System.out.println( "Members, who failed the course: " + (members.length - succeeded) );
    }

    public int size() {
        return members.length;
    }
}
