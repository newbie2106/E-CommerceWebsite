import { addDoc, collection, onSnapshot, orderBy, query, Timestamp } from "firebase/firestore";
import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../App";
import { db } from "../configs/firebase";

const Chat = () => {
    const [message, setMessage] = useState('');
    const [messages, setMessages] = useState([]);
    const [loading, setLoading] = useState(true);
    const { roomId } = useParams();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    useEffect(() => {
        const q = query(collection(db, `forumtech`), orderBy('createdAt'));
        const unsubscribe = onSnapshot(q, (snapshot) => {
            let msgs = [];
            snapshot.forEach(doc => {
                msgs.push({ ...doc.data(), id: doc.id });
            });
            setMessages(msgs);
        });
        setLoading(false);

        return () => {
            unsubscribe();
        }
    }, []);

    const sendMessage = async () => {
        if (message.trim()) {
            await addDoc(collection(db, `forumtech`), {
                content: message,
                createdAt: Timestamp.now(),
                name: `${user.firstName} ${user.lastName}`,
                username: user.username,
                avatar: user.avatar
            });
            setMessage('');
        }
    };

    const renderItem = (item) => {
        const isSentByCurrentUser = item.username === user.username;
        const createdAt = item.createdAt ? calculateTimeDifference(item.createdAt) : "Unknown time";
        return (
            <div className={`flex ${isSentByCurrentUser ? 'justify-end' : 'justify-start'} items-start`} key={item.id}>
                {!isSentByCurrentUser && (
                    <img 
                        className="w-8 h-8 rounded-full mr-2" 
                        src={item.avatar} 
                        alt="avatar" 
                    />
                )}
                <div className={`p-3 m-2 max-w-xs rounded-lg text-white ${isSentByCurrentUser ? 'bg-blue-500' : 'bg-gray-600'}`}>
                    <p className="text-xl text-blue-700">{item.name}</p>
                    <p>{item.content}</p>
                    <p className="text-xs text-gray-300 mt-1">{createdAt}</p>
                </div>
            </div>
        );
    };

    const calculateTimeDifference = (timestamp) => {
        const now = new Date();
        const createdAt = timestamp.toDate ? timestamp.toDate() : new Date(timestamp);
        const diffInMs = now - createdAt;
        const diffInMinutes = Math.floor(diffInMs / (1000 * 60));
        const diffInHours = Math.floor(diffInMs / (1000 * 60 * 60));
        const diffInDays = Math.floor(diffInMs / (1000 * 60 * 60 * 24));

        if (diffInDays > 0) {
            return `${diffInDays} ngày trước`;
        } else if (diffInHours > 0) {
            return `${diffInHours} giờ trước`;
        } else if (diffInMinutes > 0) {
            return `${diffInMinutes} phút trước`;
        } else {
            return 'Vừa xong';
        }
    };

    return (
        <div className="flex flex-col h-screen justify-between items-center">
            {/* Message List */}
            <div className="overflow-y-auto p-4 flex-grow bg-gray-100 w-3/5 max-w-3xl">
                {loading ? <p>Loading...</p> : messages.map(renderItem)}
            </div>

            {/* Input Area */}
            <div className="bg-white p-4 flex items-center border-t w-3/5 max-w-3xl">
                <input
                    className="flex-grow p-3 border rounded-lg focus:outline-none focus:ring focus:ring-blue-300 h-12"
                    type="text"
                    placeholder="Nhập tin nhắn..."
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
                />
                <button
                    className="ml-4 bg-blue-500 text-white px-4 py-3 rounded-lg hover:bg-blue-600 h-12"
                    onClick={sendMessage}
                >
                    Gửi
                </button>
            </div>
        </div>
    );
};

export default Chat;
