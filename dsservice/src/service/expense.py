from pydantic import BaseModel, Field
from typing import Optional

class Expense(BaseModel):
    amount: Optional[str] = Field(title="expense", description="expense made in the transaction")
    merchant: Optional[str] =  Field(title="merchant", description="merchant name whom the transaction was made")
    currency: Optional[str] = Field(title="currency", description="currency of transaction")
    bank: Optional[str] = Field(title="bank", description="bank name")
    date: Optional[str] = Field(title="date", description="date of transaction")
