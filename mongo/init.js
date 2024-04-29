console.log('INIT - authenticating as >', process.env.MONGO_INITDB_ROOT_USERNAME);
db.getSiblingDB('admin').auth(
  process.env.MONGO_INITDB_ROOT_USERNAME,
  process.env.MONGO_INITDB_ROOT_PASSWORD
);
console.log('INIT - creating user >', process.env.MONGO_USER);
db.createUser({
  user: process.env.MONGO_USER,
  pwd: process.env.MONGO_PASS,
  roles: ["readWrite"],
});
console.log('INIT - done');
