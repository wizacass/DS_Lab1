/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2014 09 23
 * <p>
 * Tai pirmoji kompleksinės duomenų struktūros klasė, kuri leidžia apjungti
 * atskirus objektus į vieną visumą - sąrašą. Objektai, kurie bus dedami į
 * sąrašą, turi tenkinti interfeisą Comparable<E>.
 * <p>
 * Užduotis: Peržiūrėkite ir išsiaiškinkite pateiktus metodus. Metodų algoritmai
 * yra aptarti paslaitos metu. Realizuokite nurodytus metodus.
 * ****************************************************************************
 */
package edu.ktu.ds.lab1b.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Koreguota 2015-09-18
 *
 * @param <E> Sąrašo elementų tipas (klasė)
 * @author Aleksis
 */
public class LinkedList<E extends Comparable<E>>
        implements List<E>, Iterable<E>, Cloneable
{
    private Node<E> first;   // rodyklė į pirmą mazgą
    private Node<E> last;    // rodyklė į paskutinį mazgą
    private Node<E> current; // rodyklė į einamąjį mazgą, naudojama getNext
    private int size;        // sąrašo dydis, tuo pačiu elementų skaičius

    /**
     * Constructs an empty list.
     */
    public LinkedList() { }

    public void addLast(E e)
    {
        if(e == null)
        {
            return;
        }

        if(first == null)
        {
            first = new Node<>(e, first);
            last = first;
        }
        else
        {
            var node = new Node<>(e, null);
            last.next = node;
            last = node;
        }
        this.size++;
    }

    public boolean removeLastOccurrence(E e)
    {
        if (e == null)
        {
            return false;
        }

        int index = -1;
        int i = 0;
        for (var currentNode = first; currentNode != null; currentNode = currentNode.next)
        {
            if(currentNode.element == e)
            {
                index = i;
            }

            i++;
        }

        if(index < 0)
        {
            return false;
        }
        else
        {
            var element = this.remove(index);
            return element != null;
        }
    }

    public void removeRange(int fromIndex, int toIndex)
    {
        if(fromIndex > toIndex)
        {
            throw new IllegalArgumentException("From index is greater than to index!");
        }

        if(fromIndex < 0)
        {
            throw new IllegalArgumentException("Index cannot be less than 0!");
        }

        if(toIndex > this.size)
        {
            throw new IndexOutOfBoundsException(String.format("List has only %d elements!", this.size));
        }

        for(int i = fromIndex; i < toIndex; i++)
        {
            this.remove(i);
        }
    }

    /**
     * metodas add įdeda elementą į sąrašo pabaigą
     *
     * @param e - tai įdedamas elementas (jis negali būti null)
     * @return true, jei operacija atlikta sėkmingai
     */
    @Override
    public boolean add(E e)
    {
        if (e == null)
        {
            return false;   // nuliniai objektai nepriimami
        }
        if (first == null)
        {
            first = new Node<>(e, first);
            last = first;
        }
        else
        {
            Node<E> e1 = new Node(e, null);
            last.next = e1;
            last = e1;
        }
        size++;
        return true;
    }

    /**
     * Įterpia elementą prieš k-ąją poziciją
     *
     * @param k pozicija
     * @param e įterpiamasis elementas
     * @return jei k yra blogas, grąžina null
     */
    @Override
    public boolean add(int k, E e)
    {
        if (e == null)
        {
            return false;
        }
        if (k < 0 || k >= size)
        {
            return false;
        }

        var newNode = new Node<E>(e, null);

        if (k == 0)
        {
            newNode.next = first;
            first = newNode;
            size++;
            return true;
        }

        int i = 0;
        for (var currentNode = first; currentNode != null; currentNode = currentNode.next)
        {
            if (i == k - 1)
            {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
                size++;
                return true;
            }

            i++;
        }

        return false;
    }

    /**
     * @return sąrašo dydis (elementų kiekis)
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * patikrina ar sąrašas yra tuščias
     *
     * @return true, jei tuščias
     */
    @Override
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * Išvalo sąrašą
     */
    @Override
    public void clear()
    {
        size = 0;
        first = null;
        last = null;
        current = null;
    }

    /**
     * Grąžina elementą pagal jo indeksą (pradinis indeksas 0)
     *
     * @param k indeksas
     * @return k-ojo elemento reikšmę; jei k yra blogas, gąžina null
     */
    @Override
    public E get(int k)
    {
        if (k < 0 || k >= size)
        {
            return null;
        }
        current = first.findNode(k);
        return current.element;
    }

    /**
     * Pakeičia k-ojo elemento reikšmę
     *
     * @param k keičiamo elemento indeksas
     * @param e nauja elemento reikšmė
     * @return senoji reikšmė
     */
    @Override
    public E set(int k, E e)
    {
        if (k < 0 || k >= size)
        {
            throw new IndexOutOfBoundsException(String.format("Linked list has only %d elements!", size));
        }

        int i = 0;
        for (var currentNode = first; currentNode != null; currentNode = currentNode.next)
        {
            if (k == i)
            {
                var currentValue = currentNode.element;
                currentNode.element = e;

                return currentValue;
            }

            i++;
        }

        return null;
    }

    /**
     * pereina prie kitos reikšmės ir ją grąžina
     *
     * @return kita reikšmė
     */
    @Override
    public E getNext()
    {
        if (current == null)
        {
            return null;
        }
        current = current.next;
        if (current == null)
        {
            return null;
        }
        return current.element;
    }

    /**
     * Šalina elementą pagal indeksą
     *
     * @param k šalinamojo indeksas
     * @return pašalintas elementas
     */
    @Override
    public E remove(int k)
    {
        if (k < 0 || k >= size)
        {
            throw new IndexOutOfBoundsException(String.format("Linked list has only %d elements!", size));
        }

        if (k == 0)
        {
            var element = first.element;
            first = first.next;
            size--;

            return element;
        }

        int i = 0;
        for (var currentNode = first; currentNode != null; currentNode = currentNode.next)
        {
            if (k == i + 1)
            {
                var element = currentNode.next.element;
                currentNode.next = currentNode.next.next;
                size--;

                return element;
            }

            i++;
        }

        return null;
    }

    /**
     * @return sąrašo kopiją
     */
    @Override
    public LinkedList<E> clone()
    {
        LinkedList<E> cl = null;
        try
        {
            cl = (LinkedList<E>) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            Ks.ern("Blogai veikia ListKTU klasės protėvio metodas clone()");
            System.exit(1);
        }
        if (first == null)
        {
            return cl;
        }
        cl.first = new Node<>(this.first.element, null);
        Node<E> e2 = cl.first;
        for (Node<E> e1 = first.next; e1 != null; e1 = e1.next)
        {
            e2.next = new Node<>(e2.element, null);
            e2 = e2.next;
            e2.element = e1.element;
        }
        cl.last = e2.next;
        cl.size = this.size;
        return cl;
    }
    //  !

    /**
     * Formuojamas Object masyvas (E tipo masyvo negalima sukurti) ir surašomi
     * sąrašo elementai
     *
     * @return sąrašo elementų masyvas
     */
    public Object[] toArray()
    {
        Object[] a = new Object[size];
        int i = 0;
        for (Node<E> e1 = first; e1 != null; e1 = e1.next)
        {
            a[i++] = e1.element;
        }
        return a;
    }

    /**
     * Masyvo rikiavimas Arrays klasės metodu sort
     */
    public void sortSystem()
    {
        Object[] a = this.toArray();
        Arrays.sort(a);
        int i = 0;
        for (Node<E> e1 = first; e1 != null; e1 = e1.next)
        {
            e1.element = (E) a[i++];
        }
    }

    /**
     * Rikiavimas Arrays klasės metodu sort pagal komparatorių
     *
     * @param c komparatorius
     */
    public void sortSystem(Comparator<? super E> c)
    {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        int i = 0;
        for (Node<E> e1 = first; e1 != null; e1 = e1.next)
        {
            e1.element = (E) a[i++];
        }
    }

    /**
     * Sąrašo rikiavimas burbuliuko metodu
     */
    public void sortBubble()
    {
        if (first == null)
        {
            return;
        }
        for (; ; )
        {
            boolean jauGerai = true;
            Node<E> e1 = first;
            for (Node<E> e2 = first.next; e2 != null; e2 = e2.next)
            {
                if (e1.element.compareTo(e2.element) > 0)
                {
                    E t = e1.element;
                    e1.element = e2.element;
                    e2.element = t;
                    jauGerai = false;
                }
                e1 = e2;
            }
            if (jauGerai)
            {
                return;
            }
        }
    }

    /**
     * Sąrašo rikiavimas burbuliuko metodu pagal komparatorių
     *
     * @param c komparatorius
     */
    public void sortBubble(Comparator<? super E> c)
    {
        if (first == null)
        {
            return;
        }
        for (; ; )
        {
            boolean sorted = true;
            Node<E> e1 = first;
            for (Node<E> e2 = first.next; e2 != null; e2 = e2.next)
            {
                if (c.compare(e1.element, e2.element) > 0)
                {
                    E t = e1.element;
                    e1.element = e2.element;
                    e2.element = t;
                    sorted = false;
                }
                e1 = e2;
            }
            if (sorted)
            {
                return;
            }
        }
    }

    /**
     * Sukuria iteratoriaus objektą sąrašo elementų peržiūrai
     *
     * @return iteratoriaus objektą
     */
    @Override
    public java.util.Iterator<E> iterator()
    {
        return new Iterator();
    }

    /**
     * Iteratoriaus metodų klasė
     */
    class Iterator implements java.util.Iterator<E>
    {
        private Node<E> iterPosition;

        Iterator()
        {
            iterPosition = first;
        }

        @Override
        public boolean hasNext()
        {
            return iterPosition != null;
        }

        @Override
        public E next()
        {
            E d = iterPosition.element;
            iterPosition = iterPosition.next;
            return d;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Studentams reikia realizuoti remove()");
        }
    }

    /**
     * Vidinė mazgo klasė
     *
     * @param <E> mazgo duomenų tipas
     */
    private static class Node<E>
    {
        private E element;    // ji nematoma už klasės ListKTU ribų
        private Node<E> next; // next - kaip įprasta - nuoroda į kitą mazgą

        Node(E data, Node<E> next)
        { //mazgo konstruktorius
            this.element = data;
            this.next = next;
        }

        /**
         * Suranda sąraše k-ąjį mazgą
         *
         * @param k ieškomo mazgo indeksas (prasideda nuo 0)
         * @return surastas mazgas
         */
        public Node<E> findNode(int k)
        {
            Node<E> e = this;
            for (int i = 0; i < k; i++)
            {
                e = e.next;
            }
            return e;
        }
    } // klasės Node pabaiga
}
