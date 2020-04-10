# YumSpot

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview
### Description
YumSpot is an app that allows users can search good places to eat, upload and share photos of food, and make appointments to eat out.

### App Evaluation
- **Category:** Social Networking / Food
- **Mobile:** This app would be developed for mobile. Functionality would be limited to mobile devices. This app uses map and camera.
- **Story:** users can search good places to eat, upload and share photos of food, and make appointments to eat out.
- **Market:** Any individual could choose to use this app whoever enjoys food and likes to eat out. Sharing photos and talking with friends about food will allow users to have more exciting experience.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how often they are talking about food and eating out.
- **Scope:** First we would start with sharing food interests. We would like to expand to delivery and advertisement features.


## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Users log in to access previous chats and preferred restaurants.
* Users can search restaurants by location and name.
* Users can select food categories to see the list of restaurants.
* Users can post photos of food. 
* Users can talk with their friend.
* Settings (profile, logout)

**Optional Nice-to-have Stories**

* Include a map on detailed page of restaurant. 
* Share map in chat.
* Show posted photos by a user on profile page.
* Include a like button to save user's favorite places.
* Include a phone call feature

### 2. Screen Archetypes

* Login
   * Users can log in
* Register - User signs up or logs into their account
   * Users can register a new account
* Category Screen
   * Food category buttons 
* Search Screen
   * Users can search restaurants by name or location.
   * Users can see the list of restaurants.
* Restaurant Info Screen
   * Users can see information of a restaurant such as phone number, location, opening hours, etc.
* Feed Screen
   * Users can view photos from other users.
* Post Screen
   * Users can post photos and comment on feed.
* Chat Screen
   * Allows user to chat with their friends
* Settings Screen
   * Lets users can go to their profile page and log out.
* Profile Screen
   * Users can view their own posts.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Restaurant
* Feed
* Chat
* Settings

**Flow Navigation** (Screen to Screen)
* Login Screen (Forced Log-in) -> Register Screen (Account creation if no log in is available)
* Category Screen (selection) -> Search Screen -> Restaurant Info Screen
* Feed Screen -> Post Screen (if post button is clicked)
* Chat Screen
* Settings Screen -> Profile Screen (if profile button is clicked)
                  -> Login Screen (if logout button is clicked)

### [BONUS] Digital Wireframes & Mockups
<img src="https://github.com/Codepath-group-2/codepath-group2/blob/master/YumSpot.JPG" height=700>

### [BONUS] Interactive Prototype
<img src="https://github.com/Codepath-group-2/codepath-group2/blob/master/Walkthrough.gif" width=300>


## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | image         | File     | image that user posts |
   | objectId      | String   | unique id for the user post (default field) |
   | author        | Pointer to User| image author |
   | commentsCount | Number   | number of comments that has been posted to an image |
   | likesCount    | Number   | number of likes for the post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |
### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all posts where user is author
         ```swift
         let query = PFQuery(className:"Post")
         query.whereKey("author", equalTo: currentUser)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (posts: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let posts = posts {
               print("Successfully retrieved \(posts.count) posts.")
           // TODO: Do something with posts...
            }
         }
         ```
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Create Post Screen
      - (Create/POST) Create a new post object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image
#### [OPTIONAL:] Existing API Endpoints
##### An API of Yelp's Business
- Base URL - [https://api.yelp.com/v3/businesses](https://api.yelp.com/v3/businesses/search)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /search | returns up to 1000 businesses based on the provided search criteria
    `GET`    | /{id} | returns detailed business content
    `GET`    | /{id}/reviews | returns up to three review excerpts for a given business
