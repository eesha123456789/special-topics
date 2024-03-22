// I am claiming authorship and acknowledge the class academic integrity and collaboration policy
// the hardest part of this assignment was the findHorizontalSeam and findVerticalSeam functions because I didn't understtand that they were flipped versions of each other. I eventually realized that vertical was looking top to bottom while horizontal was left to right.
import java.awt.Color;

import edu.princeton.cs.algs4.Picture;


public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    private double[][] energy;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture1){
        if (picture1 == null)
            throw new IllegalArgumentException();
        picture = new Picture(picture1);
        width = picture.width();
        height = picture.height();

        energy = new double[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                energy[row][col] = energy(col, row);
            }
        }
    }

    // current picture
    public Picture picture(){
        return new Picture(picture);
    }

    // width of current picture
    public int width(){
        return width;
    }

    // height of current picture
    public int height(){
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y){
        if (!(x > -1 && y > -1 && x < width() && y < height())){
            throw new IllegalArgumentException();
        }
        if (x == 0 || y == 0 || x == width - 1 ||  y == height - 1){
            return 1000d;
        }

        Color x1 = picture.get(x+1,y);
        Color x2 = picture.get(x-1,y);
        Color y1 = picture.get(x,y+1);
        Color y2 = picture.get(x,y-1);

        double redx = Math.pow(x1.getRed() - x2.getRed(),2);
        double greenx = Math.pow(x1.getGreen() - x2.getGreen(),2);
        double bluex = Math.pow(x1.getBlue() - x2.getBlue(),2);
        double redy = Math.pow(y1.getRed() - y2.getRed(),2);
        double greeny = Math.pow(y1.getGreen() - y2.getGreen(),2);
        double bluey = Math.pow(y1.getBlue() - y2.getBlue(),2);

        return Math.sqrt(redx + greenx + bluex + redy + greeny + bluey);
    }


    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam(){
        double[][] energy = new double[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) 
                energy[x][y] = energy(x, y);
        }
    
        int[][] edgeTo = new int[width][height];
        double[][] distance = new double[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                distance[x][y] = Double.POSITIVE_INFINITY;
                if (x == 0)
                    distance[x][y] = energy[x][y];
            }
        }
    
        for (int x = 0; x < width - 1; x++) {
            for (int y = 0; y < height; y++) {
                if (distance[x + 1][y] > distance[x][y] + energy[x + 1][y]) {
                    distance[x + 1][y] = distance[x][y] + energy[x + 1][y];
                    edgeTo[x + 1][y] = y;
                }
                if (y > 1) {
                    if (distance[x + 1][y - 1] > distance[x][y] + energy[x + 1][y - 1]) {
                        distance[x + 1][y - 1] = distance[x][y] + energy[x + 1][y - 1];
                        edgeTo[x + 1][y - 1] = y;
                    }
                }
                if (y + 1 < height) {
                    if (distance[x + 1][y + 1] > distance[x][y] + energy[x + 1][y + 1]) {
                        distance[x + 1][y + 1] = distance[x][y] + energy[x + 1][y + 1];
                        edgeTo[x + 1][y + 1] = y;
                    }
                }
            }
        }
    
        int lowRow = 0;
        double lowEnergy = Double.POSITIVE_INFINITY;
        for (int y = 0; y < height; y++) {
            if (lowEnergy > distance[width - 1][y]) {
                lowEnergy = distance[width - 1][y];
                lowRow = y;
            }
        }
    
        int[] fin = new int[width];
        int lowColumn = width - 1;
        while (lowColumn >= 0) {
            fin[lowColumn] = lowRow;
            lowRow = edgeTo[lowColumn--][lowRow];
        }
    
        return fin;

    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam(){
        double[][] energy = new double[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                energy[x][y] = energy(x, y);
            }
        }

        int[][] edgeTo = new int[width][height];
        double[][] distance = new double[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                distance[x][y] = Double.POSITIVE_INFINITY;
                if (y == 0) 
                    distance[x][y] = energy[x][y];
            }
        }

        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                if (distance[x][y + 1] > distance[x][y] + energy[x][y + 1]) {
                    distance[x][y + 1] = distance[x][y] + energy[x][y + 1];
                    edgeTo[x][y + 1] = x;
                }
                if (x > 1) {
                    if (distance[x - 1][y + 1] > distance[x][y] + energy[x - 1][y + 1]) {
                        distance[x - 1][y + 1] = distance[x][y] + energy[x - 1][y + 1];
                        edgeTo[x - 1][y + 1] = x;
                    }
                }
                if (x + 1 < width) {
                    if (distance[x + 1][y + 1] > distance[x][y] + energy[x + 1][y + 1]) {
                        distance[x + 1][y + 1] = distance[x][y] + energy[x + 1][y + 1];
                        edgeTo[x + 1][y + 1] = x;
                    }
                }
            }
        }

        int lowColumn = 0;
        double lowEnergy = Double.POSITIVE_INFINITY;
        for (int x = 0; x < width; x++) {
            if (lowEnergy > distance[x][height - 1]) {
                lowEnergy = distance[x][height - 1];
                lowColumn = x;
            }
        }
        
        int[] fin = new int[height];
        int lowRow = height - 1;
        while (lowRow >= 0) {
            fin[lowRow] = lowColumn;
            lowColumn = edgeTo[lowColumn][lowRow--];
        }

        return fin; 
    }

    private void validateSeams(int[] seam, boolean side){
        if (seam == null || (side && seam.length != height) || (!side && seam.length != width))
            throw new IllegalArgumentException();
        
        for(int i : seam){
            if (i < 0  || (side && i >= width) || (!side && i >= height))
                throw new IllegalArgumentException();
        }
        for (int j = 0; j < seam.length - 1; j++){
            if (Math.abs(seam[j] - seam[j + 1]) > 1)
                throw new IllegalArgumentException();
        }
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam){
        validateSeams(seam, false);
        Picture temp = new Picture(width, height - 1);

        for (int x = 0; x < width; x++){
            int checkSpecific = 0;
            for (int y = 0; y < height - 1; y++){
                if (seam[x] == y)
                    checkSpecific = 1;
                temp.set(x, y, picture.get(x, y + checkSpecific));
            }
        }
        picture = temp;
        width = picture.width();
        height = picture.height();
        
        double[][] tempEnergy = new double[height][width];
    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) 
                tempEnergy[y][x] = energy(x, y);
        }
        energy = tempEnergy;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam){
        validateSeams(seam, true);
        Picture temp = new Picture(width - 1, height);

        for(int y = 0; y < height; y++){
            int checkSpecific = 0;
            for(int x = 0; x < width - 1; x++){
                if (seam[y] == x){
                    checkSpecific = 1;
                }
                temp.set(x, y, picture.get(x + checkSpecific, y));
            }
        }
        picture = temp;
        width = picture.width();
        height = picture.height();
        
        double[][] tempEnergy = new double[height][width];
    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) 
                tempEnergy[y][x] = energy(x, y);
        }
        energy = tempEnergy;

    }

    //  unit testing (optional)
    public static void main(String[] args){}

    }
