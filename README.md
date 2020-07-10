Original App Design Project - README
===

# COLLAB

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
[A social media app which allows creators from different crafts to get together, connect, grow and collaborate with each other]

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Photo & Video / Social
- **Mobile:** Uses camera and it is mobile first experience.
- **Story:** Allows users to show their own artwork and collaborate with other artists.
- **Market:** Anyone that is interested in expanding their bussiness and growing their social-media through collaborating and working with other artists could enjoy this app. Gives users the ability to find other users with similar interests.
- **Habit:** Users can post throughout the day many times.
- **Scope:**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can register
* User can view other users' profiles
* User (registered) can add item to database
* User (registered) can post to collab
* User (registered) can follow another user
* [Bottom Navigation]: Four fragments with home feed, personal profile, compose picture and discovery feed.
* [Discovery Feed]: Will use API calls to show the latest trends in music, art, fashion, etc.
* [Home Feed]: Will show friends that you have added in the app and the work they have recently created.

**Optional Nice-to-have Stories**

* [Chat Room] Add additional menu items inside the bottom navigation menu to allow users to communicate with each other.
* [Pull-to-Refresh] Implement pull to refresh on home timeline.
* [Viewing other users profiles] If a user clicks on a profile, they can view all the posts they have.
* User (registered) can follow another user

## 2. Navigation

**Tab Navigation** (Tab to Screen)

* Home Feed
* Profile
* Discovery Page

**Flow Navigation** (Screen to Screen)

* Login Screen
	=> Home

* Home
	=> Posts
	=> User Details

* Discover Page
	=> List of Topics

* Creation
	=> Home

* User
	=> Profile Page

* Profile Page
  => Posts
  => Compose Page

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src='pictures/wireframe.png' title='Video Walkthrough' width='' alt='Video Walkthrough' />

## Schema
[This section will be completed in Unit 9]

### Models

#### User

| Property           | Type           | Description                                       |
|:------------------ |:-------------- |:------------------------------------------------- |
| objectId           | String         | unique id for the user (default field)            |
| email              | String         | email user has set for account                    |
| username           | String         | display name of the user                          |
| following          | Array\<String> | list of pointers to users they are following      |
| followers          | Array\<String> | list of pointers to users that are following them |
| createdProjects    | Array\<String> | list of pointers to their posts they have created |
| createdAt          | DateTime       | date when user is created (default field)         |


#### Project

| Property     | Type            | Description                                             |
| ------------ |:--------------- |:------------------------------------------------------- |
| objectId     | String          | unique id for the project                               |
| name         | String          | display name of the project                             |
| description  | String          | description of the project                              |
| picture      | ParseFile       | image of the work they would like to show               |
| videoUrl     | String          | url for the video tutorial of the project               |
| likesCount   | Number          | number of likes for the project                         |
| user         | Pointer to User | pointer to User that submitted this project             |
| createdAt    | DateTime        | date when user is created (default field)               |



### Networking
