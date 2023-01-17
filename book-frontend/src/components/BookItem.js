import React from 'react';
import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

//{id,title,author} => 구조분할 할당
const BookItem = (props) => {
  const { id, title, author } = props.book;
  return (
    <Card className="m-2">
      <Card.Body>
        <Card.Title>{title}</Card.Title>
        <Link to={'/book/' + id} className="btn btn-primary">
          상세보기
        </Link>
      </Card.Body>
    </Card>
  );
};

export default BookItem;
