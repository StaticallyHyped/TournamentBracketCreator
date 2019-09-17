# TournamentBracketCreator
A Java Android app I created for a Table Tennis club that I'm a member of. Users can add players to a player pool, which can then generate a double elimination tournament bracket from that pool. Historical Win/Loss data is stored on an AWS server which users can view within the app. From the Win/Loss records, players are given a power rating based on a modified Elo Ratings System algorithm, which can also be viewed by anyone with the app. 

PLUGINS/DEPENDENCIES:
- apply plugins: com.amazonaws.appsync, com.android.application, com.apollographql.android
- npm - check to see if it's already installed on your system using npm -v
if not, install npm using npm install in your project's root folder

- AWS amplify - check to see if there's a version installed already using amplify -v
if not, install amplify using amplify install

Use this as a guide if you don't have NPM, Node.JS, or Amplify already installed: https://aws-amplify.github.io/docs/

It is recommended to have a fully built, or near-fully built, DynamoDB api set up, with schema, data sources and resolvers, before running the next step. It is possible to update your schema after your apollo code has been generated, but it takes extra steps. 
Once your back end is established, run amplify init to begin the process of pulling your data from the cloud. Click Y (the default) until you get to the step where they ask if you want to create a new API. Say no, and select the API you created previously and click through the defaults. Run amplify push and wait for the data to be retrieved. Once retrieved, apollo should generate your .java, .graphql, .json, and .schema files. If for some reason it fails to retrieve the data, run amplify gql-compile to run it again. 

