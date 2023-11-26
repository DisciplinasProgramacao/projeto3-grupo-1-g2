package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTool {
    private FileReader file_reader;
    private FileWriter file_writer;
    private BufferedReader buffered_reader;
    private BufferedWriter buffered_writer;
    private boolean append;

    /**
     * @param path
     *             Construtor do FileTool
     */
    public FileTool(boolean append) throws IOException {
        this.append = append;
    }

    /**
     * @param index
     *              Retorna linha de leitura do arquivo
     */
    public String readLine(int index) throws IOException {
        String line;
        int counter = 1;
        String text = null;

        while ((line = buffered_reader.readLine()) != null) {
            if (counter == index) {
                text = line;
                break;
            }
            counter++;
        }
        if (text == null)
            return "";

        return text;
    }

    /**
     * Retorna uma string com a leitura do arquivo por completo
     */
    public String readAll() throws IOException {
        String line;
        String text = "";

        while ((line = buffered_reader.readLine()) != null) {
            text += line + "\n";
        }

        return text;
    }

    /**
     * @param text
     *             Busca String dentro do arquivo procurado e retorna caso encontre
     */
    public String findText(String text) throws IOException {
        String line;
        String text_not_found = "Texto não encontrado.";

        while ((line = buffered_reader.readLine()) != null) {
            if (line.contains(text)) {
                return line;
            }
        }

        return text_not_found;
    }

    /**
     * Escreve no arquivo em uma linha nova.
     */
    public boolean writeNewLine(String text) throws IOException {
        buffered_writer.newLine();
        buffered_writer.write(text);

        return true;
    }

    /**
     * @param text
     *             Escreve no arquivo.
     */
    public boolean write(String text) throws IOException {
        buffered_writer.write(text);

        return true;
    }

    /**
     * Fecha as leituras dos arquivos.
     */
    public void close() throws IOException {
        buffered_reader.close();
        buffered_writer.close();
        file_reader.close();
        file_writer.close();
    }

    public boolean createFile(String path, String fileName) throws IOException {
        File newFile = new File(path + fileName + ".txt");

        boolean created = newFile.createNewFile();
        return created;
    }

    public void changePath(String path) throws IOException {
        this.file_reader = new FileReader(path);
        this.file_writer = new FileWriter(path, this.append);
        this.buffered_reader = new BufferedReader(file_reader);
        this.buffered_writer = new BufferedWriter(file_writer);
    }
}