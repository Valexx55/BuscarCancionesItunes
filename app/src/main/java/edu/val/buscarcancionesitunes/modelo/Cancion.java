package edu.val.buscarcancionesitunes.modelo;

public class Cancion {

    private long trackId;
    private String artistName;
    private String collectionName;
    private String artworkUrl100;//enlace http a la imagen del disco
    private String trackName;//nombre canción
    private String previewUrl;//enlace http al mp3 canción


    public Cancion() {
    }

    public Cancion(long trackId, String artistName, String collectionName, String artworkUrl100, String trackName, String previewUrl) {
        this.trackId = trackId;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.artworkUrl100 = artworkUrl100;
        this.trackName = trackName;
        this.previewUrl = previewUrl;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "trackId=" + trackId +
                ", artistName='" + artistName + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", artworkUrl100='" + artworkUrl100 + '\'' +
                ", trackName='" + trackName + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
