from dotenv import load_dotenv
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
import os
from langchain_mistralai import ChatMistralAI
from service.expense import Expense

class LLMService:
    def __init__(self):
        load_dotenv()
        self.prompt = ChatPromptTemplate.from_messages(
            [
                (
                    "system",
                    "you are an expert extraction algorithm."
                    "only extract relevant information from the text."
                    "If you dont know the value of an attribute asked to extract, "
                    "return null for the attribute's value."
                ),
                ("human","{text}")

            ]
        )

        self.apiKey = os.getenv('LLM_API_KEY')
        self.llm = ChatMistralAI(api_key=self.apiKey, model_name="mistral-small-latest")
        self.runnable = self.prompt | self.llm.with_structured_output(schema=Expense)

    def runLLM(self, message):
        return self.runnable.invoke({"text": message})
