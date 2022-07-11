package edu.val.buscarcancionesitunes;

import java.util.List;

public class ResultadoBusquedaCanciones {

    private int resultCount;
    private List<Cancion> results;

    public ResultadoBusquedaCanciones() {
    }

    public ResultadoBusquedaCanciones(int resultCount, List<Cancion> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Cancion> getResults() {
        return results;
    }

    public void setResults(List<Cancion> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultadoBusquedaCanciones{" +
                "resultCount=" + resultCount +
                ", results=" + results +
                '}';
    }
}
