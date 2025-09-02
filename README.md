Ejemplo:

```mermaid
erDiagram
users {
  long id PK
  string name
  string surname
  long rol_id FK
}
users }|--|| rols : has
rols {
  long id PK
  string rol_name
}
users ||--|{ request : ask
request {
  long id PK
  date request_date
  string topic
  string description
  boolean is_assisted
  long user_id FK
  long topic_id FK
}
assists }|--|| request : help
assists {
  long id PK
  date assist_date
  long user_id FK
  long request_id FK
}
topic ||--|{ request : has
topic {
  long id PK
  string name
}
```

<img alt="Chen Diagram" src="assets/ER-chen-diagram.png"/>
