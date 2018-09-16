package ru.ryabtsevvv.se;

public class TeamMember {
    private int level;
    private boolean success;

    public TeamMember( int _level ) {
        level = _level;
        success = false;
    }

    public void run( Course course ) {
        for( int i = 0; i < course.length(); ++i ) {
            if( !overcome( course.getBarrier( i ) ) ) {
                return;
            }
        }
        success = true;
    }

    private boolean overcome( Barrier barrier ) {
        return level > barrier.getComplexity();
    }

    public boolean isSucceeded() {
        return success;
    }
}
