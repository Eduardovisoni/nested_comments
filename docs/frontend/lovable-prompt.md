## prompt
Build a frontend for a Spring Boot API called Nested Comments.

The API runs locally at http://localhost:8080.

Use this API behavior:
- POST /comments/root creates a root comment with JSON { "author": string, "content": string }.
- POST /comments/{parentId}/replies creates a reply under a parent comment.
- GET /tree returns the full nested tree.
- GET /tree/{commentId} returns a subtree.
- GET /comments/{commentId}/path returns ids from root to the comment.
- GET /tree/traversal?type=DFS returns DFS ids.
- GET /tree/traversal?type=BFS returns BFS ids.
- GET /tree/height returns tree height.
- GET /comments/{commentId}/depth returns comment depth.
- GET /comments/{commentId}/ancestors returns ancestor ids.
- GET /tree/validate returns true if the tree has no cycles.

Create a practical single-page app:
- A form to create a root comment.
- A form to reply to an existing comment by parentId.
- A tree view that displays nested comments.
- Buttons to refresh the tree, run DFS, run BFS, get height, and validate.
- Inputs for commentId to query path, depth, ancestors, and subtree.
- Show API results clearly in panels.
- Keep the UI clean and simple for a classroom demo.

Important:
- Use fetch.
- Base URL must be editable in the UI, defaulting to http://localhost:8080.
- Do not use mock data once the backend is running.

## Enlace

https://lovable.dev/projects/75222951-1ed1-43a0-806a-c26d0eca917a?redirectUrl=https%3A%2F%2Fchatgpt.com%2Fc%2F6a18c1cf-9284-83e8-a02c-5a430d7a5ad1