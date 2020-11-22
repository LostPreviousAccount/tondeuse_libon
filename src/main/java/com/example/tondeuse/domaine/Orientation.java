package com.example.tondeuse.domaine;


public enum Orientation implements Tournable {
    North {
        public Orientation aGauche() {
            return West;
        }

        public Orientation aDroite() {
            return East;
        }
    }, East {
        public Orientation aGauche() {
            return North;
        }

        public Orientation aDroite() {
            return South;
        }
    }, West {
        public Orientation aGauche() {
            return null;
        }

        public Orientation aDroite() {
            return null;
        }
    }, South {
        public Orientation aGauche() {
            return null;
        }

        public Orientation aDroite() {
            return null;
        }
    }

}

interface Tournable {
    Orientation aGauche();

    Orientation aDroite();
}
