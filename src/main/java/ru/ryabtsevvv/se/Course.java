package ru.ryabtsevvv.se;

public class Course {


    private Barrier[] barriers;

    public Course( int[] _barrierComplexities ) {
        barriers = new Barrier[ _barrierComplexities.length ];
        for( int i = 0; i < _barrierComplexities.length; ++i ) {
            barriers[i] = new Barrier( _barrierComplexities[ i ] );
        }
    }

    public void doIt( Team team) {
        for( int i = 0; i < team.size(); ++i ) {
            team.getMember(i).run( this );
        }
    }

    public Barrier getBarrier( int index ) {
        return barriers[ index ];
    }

    public int length() {
        return barriers.length;
    }
}
