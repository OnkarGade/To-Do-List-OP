import { useSelector } from "react-redux";
import Card from "./Card";
export default function List() {
  const { items, loading, error } = useSelector((store) => store.bag);

  return (
    <>
      <div className="mt-4 grid grid-cols-3 gap-y-7 place-items-center">
        {items.map((obj) => {
          return <Card key={obj.id} obj={obj}/>
        })}
      </div>
    </>
  );
}
