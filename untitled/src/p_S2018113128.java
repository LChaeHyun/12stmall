public class p_S2018113128 {
    public static void main(String[] args) {
        String c0 = args[0];
        String c1 = args[1];
        String hexStr = c0.substring(2);
        Long[] list = new Long[8];
        for (int i = 0; i < 16; i+=2) {
            String byteStr = hexStr.substring(i, i + 2);
            list[i / 2] = Long.parseUnsignedLong(byteStr, 16);
        }
        pad_oracle p = new pad_oracle ();
        boolean isPaddingCorrect;
        String Cp;

        Long[] I = new Long[8];
        for(int j = 0; j <8; j++) {
            System.out.println("------------------");
            Cp = c0.substring(0,2*(8- j));
            StringBuilder key = new StringBuilder();
            if(j > 0){
                for(int k=j;k>0;k--)
                    key.append(String.format("%02x", (I[8 - k] ^ (j + 1))));
            }
            System.out.println("C' : "+key);

            for (long i = 0L; i < 256L; i++) {
                isPaddingCorrect = p.doOracle(Cp+String.format("%02x"+key, i),c1);
                if (isPaddingCorrect) {
                    if(j<7){
                        String Cpp = Cp.substring(0,2*(7-j)) + String.format("%02x",j+2);
                        if(p.doOracle(Cpp+String.format("%02x"+key, i),c1)){
                            I[7-j] = i ^(j+1);
                            System.out.println("I["+(7-j)+"] : Ox" + String.format("%02x", I[7-j]));
                        }
                    }else{
                        I[7-j] = i ^(j+1);
                        System.out.println("I["+(7-j)+"] : Ox" + String.format("%02x", I[7-j]));
                    }
                    
                }
            }


        }
        System.out.println("------------------\n");
        StringBuilder plainStr = new StringBuilder();
        plainStr.append("Ox");
        for(int i=0;i<8;i++){
            plainStr.append(String.format("%02x",I[i]));
        }
        System.out.println("plain : "+plainStr);
    }
}

