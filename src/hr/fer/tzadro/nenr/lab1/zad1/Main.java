package hr.fer.tzadro.nenr.lab1.zad1;

public class Main {

    public static void main(String[] args) {
        IDomain d1 = Domain.intRange(0, 5); // {0,1,2,3,4}
        Debug.print(d1, "Elementi domene d1:");

        IDomain d2 = Domain.intRange(0, 3); // {0,1,2}
        Debug.print(d2, "Elementi domene d2:");

        IDomain d3 = Domain.combine(d1, d2);
        Debug.print(d3, "Elementi domene d3:");

        System.out.println(d3.elementForIndex(0));
        System.out.println(d3.elementForIndex(5));
        System.out.println(d3.elementForIndex(14));
        System.out.println(d3.indexOfElement(DomainElement.of(4, 1)));
    }
}

/*
Elementi domene d1:
Element domene: 0
Element domene: 1
Element domene: 2
Element domene: 3
Element domene: 4
Kardinalitet domene je: 5

Elementi domene d2:
Element domene: 0
Element domene: 1
Element domene: 2
Kardinalitet domene je: 3

Elementi domene d3:
Element domene: (0,0)
Element domene: (0,1)
Element domene: (0,2)
Element domene: (1,0)
Element domene: (1,1)
Element domene: (1,2)
Element domene: (2,0)
Element domene: (2,1)
Element domene: (2,2)
Element domene: (3,0)
Element domene: (3,1)
Element domene: (3,2)
Element domene: (4,0)
Element domene: (4,1)
Element domene: (4,2)
Kardinalitet domene je: 15

(0,0)
(1,2)
(4,2)
13
 */