package cosasModificadasDeFAIAParaPodarArbol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class PrintOut {
    private File outputFile;
    private FileWriter out;

    PrintOut(String name) throws Exception {
        this(name, true);
    }

    PrintOut(String name, boolean append) throws Exception {
        this.outputFile = new File(name);

        try {
            this.out = new FileWriter(this.outputFile, append);
        } catch (IOException var4) {
            throw new Exception(var4.getMessage());
        }
    }

    public void write(String texto) throws Exception {
        try {
            this.out.write(texto);
        } catch (IOException var3) {
            throw new Exception(var3.getMessage());
        }
    }

    public void close() throws Exception {
        try {
            this.out.close();
        } catch (IOException var2) {
            throw new Exception(var2.getMessage());
        }
    }
}
