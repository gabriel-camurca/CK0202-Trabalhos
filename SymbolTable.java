import javax.naming.Binding;
import Symbol.Symbol;

public class SymbolTable{
    class Bucket {
        String key; Object binding; Bucket next;
        Bucket (String k, Object b, Bucket n){
            key = k;
            binding = b;
            next = n;
        }
    }

    class HashT {
        final int SIZE = 256;
        Bucket table[] = new Bucket[SIZE];

        private int hash(Symbol s) {
            int h=0;
            for(int i=0; i<s.toString().length(); i++) {
                h=h*65599+s.toString().charAt(i);
            }
            return h;
        }

        private void insert(String s, Binding b) {
            Symbol sy = Symbol.symbol(s);
            int index=hash(sy)%SIZE;
            table[index]=new Bucket(s,b,table[index]);
        }

        Object lookup(String s) {
            Symbol sy = Symbol.symbol(s);
            int index=hash(sy)%SIZE;
            for(Bucket b = table[index]; b!=null; b=b.next){
                if (s.equals(b.key)) return b.binding;
            }
            return null;
        }

        void pop(String s) {
            Symbol sy = Symbol.symbol(s);
            int index=hash(sy)%SIZE;
            table[index]=table[index].next;
        }
    }
}