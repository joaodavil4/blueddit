# 🔵 Blueddit — a minimal Reddit "Top" feed in Jetpack Compose

> A small Android client that shows Reddit's top posts as an infinite, pull-to-refresh feed. Written in Kotlin with Jetpack Compose, Retrofit and Coil.

---

## Project at a glance

| | |
|---|---|
| **Date** | March 11 – 19, 2022 |
| **Author** | João Davila ([@joaodavil4](https://github.com/joaodavil4)) |
| **Context** | Solo project. The repo does not state a course or employer purpose. |
| **Stack** | Kotlin, Jetpack Compose 1.1.1, Retrofit 2 + Gson, OkHttp, Coil, Accompanist SwipeRefresh, MVVM with LiveData |
| **Platform** | Android, `minSdk 27`, `targetSdk 31` |

## What it does

- Loads the **top posts** from Reddit's public JSON endpoint (`/top.json`).
- Shows each post as a card: author, relative time ("2 hours ago"), title, thumbnail and comment count.
- **Infinite scroll**: requests the next page when the user gets within 3 items of the end of the list.
- **Pull-to-refresh** to reset the feed.
- **Full-screen image dialog**: tapping a thumbnail expands it.

## Architecture

```
MainActivity  ──►  Posts (Composable)  ──►  PostItem / CircularImage / FullScreenDialog
                         │  observeAsState
                         ▼
                   FeedViewModel  ──►  PostRepository (interface)
                   (LiveData, paging)        │
                                             ▼
                                   PostRepositoryClass ──► Retrofit ──► reddit.com
```

| Package | Contents |
|---|---|
| `ui/view` | `MainActivity`, `feed/Posts` (list, refresh, paging), `feed/FeedViewModel` |
| `ui/compositions` | Reusable composables: `PostItem`, `CircularImage`, `NetworkImage`, `LoadCircularImage`, `Dialog` |
| `ui/theme` | Compose colours, shapes, typography |
| `service` | Retrofit interface, constants, response models, repository |
| `model` | `Post` (Parcelable data class) |
| `extensions` | `DateExtension`: "time ago" formatting |

## Screenshots

*Not available.* This is a Compose project from 2022 targeting an old Android Gradle Plugin, and I did not build or run it for this README, so there are no captures to show.

## Running it

Open the project in Android Studio (2021.x "Bumblebee" era, compatible with the Compose 1.1.1 / Kotlin plugin versions in `build.gradle`) and run the `app` configuration on a device or emulator with internet access. *Not verified for this README.*

## Status and limitations

- **Paging is probably wrong.** The `after` query parameter is sent as an integer offset, but Reddit's API expects a post "fullname" token. The response's `after` field is parsed but never used.
- **Errors are swallowed.** A failed request is ignored (`onFailure` does nothing), with no error or empty state in the UI.
- **Cleartext base URL.** `http://www.reddit.com/` with `usesCleartextTraffic="true"`; HTTPS would be the right default.
- **No dependency injection or caching**: the Retrofit client is rebuilt on every request, and the feed is lost on process death.
- **Old stack.** Compose 1.1.1, `lifecycle-extensions` (deprecated), `LiveData` + `CoroutineScope` implemented by the `ViewModel`, and Accompanist SwipeRefresh (since replaced by Material's pull-to-refresh).
- **Almost no tests**: only the template example tests.
- **"Layout almost done"** is the author's own last UI commit message; the app is a working prototype rather than a finished product.

## Credits

Built by João Davila. Reddit data comes from Reddit's public JSON API.
