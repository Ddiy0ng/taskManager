<h3>Task Api</h3>
<table>
  <thead>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Request</th>
      <th>Response</th>
      <th>상태코드</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>POST</td>
      <td>/api/users/{userId}/tasks</td>
      <td>{
  "password": "0000",
  "tasks": "일정"
}</td>
      <td>"일정을 생성했습니다"</td>
      <td>201 Created</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>/api/users/{userId}/tasks?pageNumber={pageNumber}</td>
      <td>요청 param</td>
      <td>[
    {
        "taskId": 2,
        "userName": "이름2",
        "tasks": "일정2",
        "postDate": "2025-05-13",
        "updateDate": "2025-05-13"
    },
    {
        "taskId": 1,
        "userName": "이름1",
        "tasks": "일정1",
        "postDate": "2025-05-13",
        "updateDate": "2025-05-13"
    }
]</td>
      <td>200 OK</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>/api/users/{userId}/tasks/{taskId}</td>
      <td>{
  "password": "0000"
}</td>
      <td>    {
        "taskId": 1,
        "userName": "이름1",
        "tasks": "일정1",
        "postDate": "2025-05-13",
        "updateDate": "2025-05-13"
    }</td>
      <td>200 OK</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>/api/users/{userId}/tasks/{taskId}</td>
      <td>{
  "password": "0000",
  "tasks": "일정"
}</td>
      <td>"일정을 수정했습니다."</td>
      <td>200 OK</td>
    </tr>
    <tr>
      <td>DELETE</td>
      <td>/api/users/{userId}/tasks/{taskId}</td>
      <td>{
  "password": "0000"
}</td>
      <td>"일정을 삭제했습니다" / "해당 데이터를 찾을 수 없습니다."</td>
      <td>200 OK / 404 Not Found</td>
    </tr>
  </tbody>
</table>

<h3>User Api</h3>
<table>
  <thead>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Request</th>
      <th>Response</th>
      <th>상태코드</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>POST</td>
      <td>/api/users</td>
      <td>{        
        "userName": "이름1",
        "email": "name1@gmail.com"
        }</td>
      <td>"유저 데이터를 생성했습니다."</td>
      <td>201 Created</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>/api/users</td>
      <td>—</td>
      <td>[
    {
        "userId": 2,
        "userName": "이름2",
        "email": "name2@gmail.com",
        "postDate": "2025-05-13",
        "updateDate": "2025-05-13"
    },
    {
        "userId": 1,
        "userName": "이름1",
        "email": "name1@gmail.com",
        "postDate": "2025-05-13",
        "updateDate": "2025-05-13"
    }
]</td>
      <td>200 OK</td>
    </tr>
    <tr>
      <td>GET</td>
      <td>/api/users/{userId}</td>
      <td>—</td>
      <td>    {
        "userId": 1,
        "userName": "이름1",
        "email": "name1@gmail.com",
        "postDate": "2025-05-13",
        "updateDate": "2025-05-13"
    }</td>
      <td>200 OK</td>
    </tr>
    <tr>
      <td>PUT</td>
      <td>/api/users/{userId}</td>
      <td>{        
        "userName": "이름1",
        "email": "name1@gmail.com"
        }</td>
      <td>"유저 데이터를 수정했습니다"</td>
      <td>200 OK</td>
    </tr>
    <tr>
      <td>DELETE</td>
      <td>/api/users/{userId}</td>
      <td>—</td>
      <td>"유저 데이터를 삭제했습니다." / "해당 ID의 데이터를 찾을 수 없습니다."</td>
      <td>200 OK / 404 Not Found</td>
    </tr>
  </tbody>
</table>
<h3>ERD</h3>

![화면 캡처 2025-05-14 021634](https://github.com/user-attachments/assets/7b19f0d8-20b4-43bc-b4b4-417a0fa71640)


