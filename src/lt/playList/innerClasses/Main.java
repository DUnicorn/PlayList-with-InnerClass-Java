package lt.playList.innerClasses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	//define as list and specify when create
	private static List<Album> albums = new ArrayList<>();

	public static void main(String[] args) {

		Album album = new Album("Super Java", "Java Master");
		album.addSong("LinkedList", 4.6);
		album.addSong("HardRock Java", 2.6);
		album.addSong("Hate&Love", 2.45);
		album.addSong("Learn ", 3.5);
		album.addSong("Never give up", 3.40);
		album.addSong("Java always Love you", 2.50);

		// new object.
		albums.add(album);

		album = new Album("Spring spring it", "Spring Framework");
		album.addSong("Give me more beans", 2.6);
		album.addSong("ID never ends", 4.6);
		album.addSong("Bugs bugs bugs", 2.45);
		album.addSong("Inject it", 3.5);
		album.addSong("Sprig spring it", 3.40);

		albums.add(album);

		LinkedList<Song> playList = new LinkedList<>();
		albums.get(0).addToPlayList("Hate&Love", playList);
		albums.get(0).addToPlayList("Java always Love you", playList);
		albums.get(0).addToPlayList("Memory", playList); // Does not exist
		albums.get(0).addToPlayList(5, playList);
		albums.get(1).addToPlayList("Inject it", playList);
		albums.get(1).addToPlayList(2, playList);
		albums.get(1).addToPlayList(54, playList);

		play(playList);

	}

	private static void play(LinkedList<Song> playList) {
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		boolean forward = true;
		ListIterator<Song> listIterator = playList.listIterator();

		if (playList.size() == 0) {
			System.out.println("No songs in playlist");
			return;
		} else {
			System.out.println("Now playing " + listIterator.next().toString());
			printMenu();
		}
		while (!quit) {
			int action = sc.nextInt();
			sc.nextLine();

			switch (action) {
			case 0:
				System.out.println("Playlist complete.");
				quit = true;
				break;
			case 1:
				if (!forward) {
					if (listIterator.hasNext()) {
						listIterator.next();
					}

					forward = true;
				}
				if (listIterator.hasNext()) {
					System.out.println("Now playing " + listIterator.next().toString());
				} else {
					System.out.println("We have reached the end of the playlist");
					forward = false;
				}
				break;
			case 2:
				if (forward) {
					if (listIterator.hasPrevious()) {
						listIterator.previous();
					}
					forward = false;
				}
				if (listIterator.hasPrevious()) {
					System.out.println("Now playing " + listIterator.previous().toString());
				} else {
					System.out.println("We are at the start of the playlist");
					forward = true;
				}
				break;
			case 3:
				if (forward) {
					if (listIterator.hasPrevious()) {
						System.out.println("Now playing " + listIterator.previous().toString());
					} else {
						System.out.println("We are at the start of the list");
					}
				} else {
					if (listIterator.hasNext()) {
						System.out.println("Now replaying " + listIterator.next().toString());
						forward = true;
					} else {
						System.out.println("We have reached the end of the list");
					}
				}
				break;
			case 4:
				printList(playList);
				break;
			case 5:
				if (playList.size() > 0) {
					listIterator.remove();
					if (listIterator.hasNext()) {
						System.out.println("Now playing " + listIterator.next());
					} else if (listIterator.hasPrevious()) {
						System.out.println("Now playing " + listIterator.previous());
					}
				}
				break;
			case 6:
				printMenu();
				break;
			}

		}

	}

	private static void printMenu() {
		System.out.println("Available actions:\npress");
		System.out.println("0 - quit\n" + "1 - to play next song\n" + "2 - to play previous song\n"
				+ "3 - to replay the current song\n" + "4 - list songs in the playlist\n"
				+ "5 - to delete current song\n" + "6 - print available actions");
	}

	private static void printList(LinkedList<Song> playList) {
		Iterator<Song> iterator = playList.iterator();

		System.out.println("=====================");
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		System.out.println("=====================");
	}

}
