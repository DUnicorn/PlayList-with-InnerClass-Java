package lt.playList.innerClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {

	private String name;
	private String artist;
	private SongList songs;

	public Album(String name, String artist) {
		super();
		this.name = name;
		this.artist = artist;
		this.songs = new SongList();
	}

	public boolean addSong(String name, double duration) {
		return this.songs.addSong(new Song(name, duration));

		// ----Old code---
		// if (findSong(name) == null) {
		// this.songs.add(new Song(name, duration));
		// return true;
		// }
		// return false;

	}

	public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
		// calling inner class method findSong.
		Song checkedSong = this.songs.findSong(trackNumber);
		if (checkedSong != null) {
			playList.add(checkedSong);
			return true;
		}

		// ----Old code-----
		// int index = trackNumber - 1;
		// if ((index >= 0) && (index <= this.songs.size())) {
		// playList.add(this.songs.get(index));
		// return true;
		// }

		System.out.println("This album does not have a track " + trackNumber);
		return false;

	}

	public boolean addToPlayList(String name, List<Song> playList) {
		Song checkedSong = songs.findSong(name);

		if (checkedSong != null) {
			playList.add(checkedSong);
			return true;
		}
		System.out.println("The song " + name + " is not in this album");
		return false;
	}

	// inner Class
	private class SongList {

		private List<Song> songs;

		public SongList() {
			this.songs = new ArrayList<Song>();
		}

		public boolean addSong(Song song) {
			if (songs.contains(song)) {
				return false;
			}
			this.songs.add(song);
			return true;
		}

		private Song findSong(String name) {
			for (Song checkedSong : this.songs) {
				if (checkedSong.getName().equals(name)) {
					return checkedSong;
				}
			}
			return null;
		}

		public Song findSong(int trackNumber) {
			int index = trackNumber - 1;
			if ((index > 0) && (index < songs.size())) {
				return songs.get(index);
			}
			return null;
		}
	}

}
