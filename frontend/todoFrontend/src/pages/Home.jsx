import Button from "../components/Button";
import Modal from "../components/Modal";
import { useSelector, useDispatch } from "react-redux";
import { toggleModal } from "../store/slices/modalSlice";
import { Toaster } from "react-hot-toast";
import List from "../components/List";
export default function Home() {
  const dispatch = useDispatch();
  const {items} = useSelector(store=>store.bag);
  
  const modalStatus = useSelector((bag) => bag.modalStatus);
  function shootModal() {
    dispatch(toggleModal(true));
  }
  return (
    <>
      <Toaster position="top-right" />
      {modalStatus && <Modal />}
      <main className="z-[1000px] min-h-screen max-w-[90%] mx-auto relative">
        <header className="border-red-500 py-6 flex items-center justify-between">
          <h1 className="font-bold text-2xl">
            Todo <span className="text-brand">Application</span>
          </h1>
          <Button func={shootModal} />
        </header>
        <p className="font-light text-[12px] text-center mt-[-.5rem]">
          Lorem ipsum, dolor sit amet consectetur adipisicing elit. Recusandae
          nostrum sit molestiae consectetur dolores, eveniet nesciunt quasi
          maiores unde. Omnis magnam sed enim obcaecati doloremque? Lorem ipsum
          dolor sit amet, consectetur adipisicing elit. Iure tempora eligendi
          ea, molestias saepe expedita! Lorem ipsum dolor sit amet, consectetur
          adipisicing elit. Soluta, tenetur.
        </p>
        {
          items.length === 0 && <h1 className="text-black text-[8rem] absolute top-[50%] left-[50%] transform -translate-x-1/2 -translate-y-1/2 w-full text-center font-bold text-gray-200">
          lets fill your stack.
        </h1>
        }
        <List/>

      </main>
    </>
  );
}
